package com.graphhopper.api;

import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter Karich
 */
public class GHMatrixBatchRequester extends GHMatrixAbstractRequester {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int maxIterations = 100;
    private long sleepAfterGET = 1000;

    public GHMatrixBatchRequester() {
        super();
    }

    public GHMatrixBatchRequester(String serviceUrl) {
        super(serviceUrl);
    }

    public GHMatrixBatchRequester(String serviceUrl, OkHttpClient client) {
        super(serviceUrl, client);
    }

    /**
     * Internal parameter. Increase only if you have very large matrices.
     */
    public GHMatrixBatchRequester setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Internal parameter. Increase only if you have very large matrices.
     */
    public GHMatrixBatchRequester setSleepAfterGET(long sleepAfterGETMillis) {
        this.sleepAfterGET = sleepAfterGETMillis;
        return this;
    }

    @Override
    public MatrixResponse route(GHMRequest ghRequest) {
        JSONObject requestJson = new JSONObject();
        List<Double[]> fromPointList = createPointList(ghRequest.getFromPoints());
        List<Double[]> toPointList = createPointList(ghRequest.getToPoints());

        List<String> outArraysList = new ArrayList<>(ghRequest.getOutArrays());
        if (outArraysList.isEmpty()) {
            outArraysList.add("weights");
        }

        // TODO allow elevation for full path
        boolean hasElevation = false;
        requestJson.put("from_points", fromPointList);
        requestJson.put("to_points", toPointList);
        requestJson.put("out_arrays", outArraysList);
        requestJson.put("vehicle", ghRequest.getVehicle());
        requestJson.put("elevation", hasElevation);

        boolean withTimes = outArraysList.contains("times");
        boolean withDistances = outArraysList.contains("distances");
        boolean withWeights = outArraysList.contains("weights");
        final MatrixResponse matrixResponse = new MatrixResponse(
                ghRequest.getFromPoints().size(),
                ghRequest.getToPoints().size(), withTimes, withDistances, withWeights);

        boolean debug = ghRequest.getHints().getBool("debug", false);
        String postUrl = buildURL("/calculate", ghRequest);        

        try {            
            String postResponseStr = postJson(postUrl, requestJson);

            if (debug) {
                logger.info("POST URL:" + postUrl + ", request:" + requestJson + ", response: " + postResponseStr);
            }

            JSONObject responseJson = toJSON(postUrl, postResponseStr);
            if (responseJson.has("message")) {
                matrixResponse.addError(new RuntimeException(responseJson.getString("message")));
                return matrixResponse;
            }
            if (!responseJson.has("job_id")) {
                throw new IllegalStateException("Response should contain job_id but was "
                        + postResponseStr + ", json:" + requestJson + ",url:" + postUrl);
            }

            final String id = responseJson.getString("job_id");
            int i = 0;
            for (; i < maxIterations; i++) {
                // SLEEP a bit and GET solution
                if (sleepAfterGET > 0) {
                    Thread.sleep(sleepAfterGET);
                }
                String getUrl = buildURL("/solution/" + id, ghRequest);

                String getResponseStr;
                try {
                    getResponseStr = getJson(getUrl);
                } catch (SocketTimeoutException ex) {
                    // if timeout exception try once again:
                    getResponseStr = getJson(getUrl);
                }

                JSONObject getResponseJson = toJSON(getUrl, getResponseStr);
                if (debug) {
                    logger.info(i + " GET URL:" + getUrl + ", response: " + getResponseStr);
                }
                matrixResponse.addErrors(GraphHopperWeb.readErrors(getResponseJson));
                if (matrixResponse.hasErrors()) {
                    break;
                } else if ("finished".equals(getResponseJson.getString("status"))) {
                    fillResponseFromJson(ghRequest, outArraysList,
                            matrixResponse, getResponseJson.getJSONObject("solution"),
                            hasElevation);
                    break;
                }
            }

            if (i >= maxIterations) {
                throw new IllegalStateException("Maximum number of iterations reached " + maxIterations + ", increasing should only be necessary for big matrices. For smaller ones this is a bug, please contact us");
            }

        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return matrixResponse;
    }

    protected final List<Double[]> createPointList(List<GHPoint> list) {
        List<Double[]> outList = new ArrayList<>(list.size());
        for (GHPoint p : list) {
            outList.add(new Double[]{p.lon, p.lat});
        }
        return outList;
    }
}
