package com.graphhopper.api;

import com.graphhopper.util.StopWatch;
import com.graphhopper.util.shapes.GHPoint;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public class GraphHopperMatrixWeb {

    public static final MediaType MT_JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient downloader = new OkHttpClient();
    private final String serviceUrl;
    private int maxIterations = 10;
    private long sleepAfterGET = 1000;
    private String key = "";

    public GraphHopperMatrixWeb() {
        this("https://graphhopper.com/api/1/matrix");
    }

    public GraphHopperMatrixWeb(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        if (serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl.substring(0, serviceUrl.length() - 1);
        }
        downloader.setConnectTimeout(5, TimeUnit.SECONDS);
    }

    public void setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
    }

    public OkHttpClient getDownloader() {
        return downloader;
    }

    /**
     * Internal parameter. Modify only if you have very large matrices.
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
     * Internal parameter. Modify only if you have very large matrices.
     */
    public void setSleepAfterGET(long sleepAfterGETMillis) {
        this.sleepAfterGET = sleepAfterGETMillis;
    }

    public GraphHopperMatrixWeb setKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalStateException("Key cannot be empty");
        }

        this.key = key;
        return this;
    }

    public MatrixResponse route(GHMRequest request) {
        StopWatch sw = new StopWatch().start();

        JSONObject requestJson = new JSONObject();
        int fromCount = request.getFromPoints().size(), toCount = request.getToPoints().size();
        List<Double[]> fromPointList = createPointList(request.getFromPoints());
        List<Double[]> toPointList = createPointList(request.getToPoints());

        List<String> outArraysList = new ArrayList<>(request.getOutArrays());
        if (outArraysList.isEmpty()) {
            outArraysList.add("weights");
        }

        requestJson.put("from_points", fromPointList);
        requestJson.put("to_points", toPointList);
        requestJson.put("out_arrays", outArraysList);
        requestJson.put("vehicle", request.getVehicle());
        // TODO allow elevation for full path
        boolean hasElevation = false;
        requestJson.put("elevation", hasElevation);

        final MatrixResponse matrixResponse = new MatrixResponse(request.getFromPoints().size(), request.getToPoints().size());

        try {
            String postUrl = serviceUrl + "/calculate?key=" + key;
            String postResponseStr = postJson(postUrl, requestJson);
            JSONObject responseJson = toJSON(postUrl, postResponseStr);
            if (responseJson.has("message")) {
                matrixResponse.addError(new RuntimeException(responseJson.getString("message")));
                return matrixResponse;
            }
            if (!responseJson.has("job_id")) {
                throw new IllegalStateException("Response should contain job_id but was " + postResponseStr + ", json:" + requestJson + ",url:" + postUrl);
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
                GraphHopperWeb.readErrors(matrixResponse.getErrors(), getResponseJson);
                if (matrixResponse.hasErrors()) {
                    break;
                } else if ("finished".equals(getResponseJson.getString("status"))) {
                    JSONObject solution = getResponseJson.getJSONObject("solution");
                    if (outArraysList.contains("paths") && solution.has("paths")) {
                        JSONArray pathArray = solution.getJSONArray("paths");
                        for (int fromIndex = 0; fromIndex < fromCount; fromIndex++) {
                            matrixResponse.newFromList();
                            JSONArray fromArray = pathArray.getJSONArray(fromIndex);
                            for (int toIndex = 0; toIndex < toCount; toIndex++) {
                                GHMResponse res = new GHMResponse(fromIndex, toIndex,
                                                                  request.getFromPoints().get(fromIndex).equals(request.getToPoints().get(toIndex)));
                                GraphHopperWeb.readPath(res, fromArray.getJSONObject(toIndex),
                                                        true, true, hasElevation);
                                matrixResponse.add(res);
                            }
                        }
                    } else {
                        boolean readWeights = outArraysList.contains("weights") && solution.has("weights");
                        boolean readDistances = outArraysList.contains("distances") && solution.has("distances");
                        boolean readTimes = outArraysList.contains("times") && solution.has("times");

                        JSONArray weightsArray = null;
                        if (readWeights) {
                            weightsArray = solution.getJSONArray("weights");
                        }
                        JSONArray timesArray = null;
                        if (readTimes) {
                            timesArray = solution.getJSONArray("times");
                        }
                        JSONArray distancesArray = null;
                        if (readDistances) {
                            distancesArray = solution.getJSONArray("distances");
                        }

                        for (int fromIndex = 0; fromIndex < fromCount; fromIndex++) {
                            matrixResponse.newFromList();

                            JSONArray weightsFromArray = null;
                            if (readWeights) {
                                weightsFromArray = weightsArray.getJSONArray(fromIndex);
                            }
                            JSONArray timesFromArray = null;
                            if (readTimes) {
                                timesFromArray = timesArray.getJSONArray(fromIndex);
                            }
                            JSONArray distancesFromArray = null;
                            if (readDistances) {
                                distancesFromArray = distancesArray.getJSONArray(fromIndex);
                            }

                            for (int toIndex = 0; toIndex < toCount; toIndex++) {
                                GHMResponse singleRsp = new GHMResponse(fromIndex, toIndex,
                                                                        request.getFromPoints().get(fromIndex).equals(request.getToPoints().get(toIndex)));
                                if (readWeights) {
                                    singleRsp.setRouteWeight(weightsFromArray.getDouble(toIndex));
                                }

                                if (readTimes) {
                                    singleRsp.setTime(timesFromArray.getLong(toIndex) * 1000);
                                }

                                if (readDistances) {
                                    singleRsp.setDistance(distancesFromArray.getDouble(toIndex));
                                }

                                matrixResponse.add(singleRsp);
                            }
                        }
                    }
                    matrixResponse.setTook(sw.stop().getSeconds());
                    break;
                }
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return matrixResponse;
    }

    private List<Double[]> createPointList(List<GHPoint> list) {
        List<Double[]> outList = new ArrayList<>(list.size());
        for (GHPoint p : list) {
            outList.add(new Double[]{p.lon, p.lat});
        }
        return outList;
    }

    protected String getJson(String url) throws IOException {
        Request okRequest = new Request.Builder().url(url).build();
        return downloader.newCall(okRequest).execute().body().string();
    }

    protected String postJson(String url, JSONObject data) throws IOException {
        Request okRequest = new Request.Builder().url(url).post(RequestBody.create(MT_JSON, data.toString())).build();
        return downloader.newCall(okRequest).execute().body().string();
    }

    protected JSONObject toJSON(String url, String str) {
        try {
            return new JSONObject(str);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot parse json " + str + " from " + url);
        }
    }
}
