package com.graphhopper.api;

import com.graphhopper.util.StopWatch;
import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public MatrixResponse route(GHMRequest ghRequest, String key) {
        StopWatch sw = new StopWatch().start();

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

        final MatrixResponse matrixResponse = new MatrixResponse(
                ghRequest.getFromPoints().size(),
                ghRequest.getToPoints().size());

        boolean debug = ghRequest.getHints().getBool("debug", false);

        try {
            String postUrl = serviceUrl + "/calculate?key=" + key;
            String postResponseStr = postJson(postUrl, requestJson);

            if (debug) {
                logger.info("POST URL:" + postUrl + ", response: " + postResponseStr);
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

            for (int i = 0; i < maxIterations; i++) {
                // SLEEP a bit and GET solution
                if (sleepAfterGET > 0) {
                    Thread.sleep(sleepAfterGET);
                }
                String getUrl = serviceUrl + "/solution/" + id + "?key=" + key;
                String getResponseStr = getJson(getUrl);
                JSONObject getResponseJson = toJSON(getUrl, getResponseStr);
                if (debug) {
                    logger.info(i + " GET URL:" + getUrl + ", response: " + getResponseStr);
                }
                GraphHopperWeb.readErrors(matrixResponse.getErrors(), getResponseJson);
                if (matrixResponse.hasErrors()) {
                    break;
                } else if ("finished".equals(getResponseJson.getString("status"))) {
                    fillResponseFromJson(ghRequest, outArraysList,
                            matrixResponse, getResponseJson.getJSONObject("solution"),
                            hasElevation);
                    break;
                }

                if (i == maxIterations) {
                    throw new IllegalStateException("Maximum number of iterations reached " + maxIterations + ", increasing should only be necessary if very big matrices. For small matrices contact us! info@graphhopper.com");
                }
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
