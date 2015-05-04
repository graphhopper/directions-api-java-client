package com.graphhopper.api;

import com.graphhopper.util.StopWatch;
import com.graphhopper.util.shapes.GHPoint;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import java.io.IOException;
import java.net.URLEncoder;
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

    private OkHttpClient downloader = new OkHttpClient();
    private final String serviceUrl = "https://graphhopper.com/api/1/matrix";
    private String key = "";

    public GraphHopperMatrixWeb() {
        downloader.setConnectTimeout(5, TimeUnit.SECONDS);
    }

    public void setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
    }

    public OkHttpClient getDownloader() {
        return downloader;
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

        int fromCount, toCount;
        String pointsStr;
        if (request.identicalLists) {
            fromCount = toCount = request.getFromPoints().size();
            pointsStr = createPointQuery(request.getFromPoints(), "point");
        } else {
            fromCount = request.getFromPoints().size();
            toCount = request.getToPoints().size();
            pointsStr = createPointQuery(request.getFromPoints(), "from_point");
            pointsStr += "&" + createPointQuery(request.getToPoints(), "to_point");
        }

        String outArrayStr = "";
        List<String> outArraysList = new ArrayList<>(request.getOutArrays());
        if (outArraysList.isEmpty()) {
            outArraysList.add("weights");
        }

        for (String type : outArraysList) {
            if (!type.isEmpty()) {
                outArrayStr += "&";
            }

            outArrayStr += "out_array=" + type;
        }

        // TODO allow elevation for full path
        boolean hasElevation = false;
        String url = serviceUrl + "?"
                + pointsStr
                + "&" + outArrayStr
                + "&vehicle=" + request.getVehicle()
                + "&key=" + key;

        MatrixResponse matrixResponse = new MatrixResponse(request.getFromPoints().size(), request.getToPoints().size());

        try {
            String str = fetchJson(url);
            JSONObject json = null;
            try {
                json = new JSONObject(str);
            } catch (Exception ex) {
                throw new RuntimeException("Cannot parse json " + str + " from " + url);
            }

            GraphHopperWeb.readErrors(matrixResponse.getErrors(), json);
            if (!matrixResponse.hasErrors()) {
                if (outArraysList.contains("paths") && json.has("paths")) {
                    JSONArray pathArray = json.getJSONArray("paths");
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
                    boolean readWeights = outArraysList.contains("weights") && json.has("weights");
                    boolean readDistances = outArraysList.contains("distances") && json.has("distances");
                    boolean readTimes = outArraysList.contains("times") && json.has("times");

                    JSONArray weightsArray = null;
                    if (readWeights) {
                        weightsArray = json.getJSONArray("weights");
                    }
                    JSONArray timesArray = null;
                    if (readTimes) {
                        timesArray = json.getJSONArray("times");
                    }
                    JSONArray distancesArray = null;
                    if (readDistances) {
                        distancesArray = json.getJSONArray("distances");
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
            }

            matrixResponse.setTook(sw.stop().getSeconds());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return matrixResponse;
    }

    private String createPointQuery(List<GHPoint> list, String pointName) {
        String pointsStr = "";
        for (GHPoint p : list) {
            if (!pointsStr.isEmpty()) {
                pointsStr += "&";
            }

            pointsStr += pointName + "=" + encode(p.lat + "," + p.lon);
        }
        return pointsStr;
    }

    public String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception ex) {
            return str;
        }
    }

    protected String fetchJson(String url) throws IOException {
        Request okRequest = new Request.Builder().url(url).build();
        return downloader.newCall(okRequest).execute().body().string();
    }
}
