package com.graphhopper.api;

import com.graphhopper.AltResponse;
import static com.graphhopper.api.GraphHopperMatrixWeb.MT_JSON;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public abstract class GHMatrixAbstractRequester {

    protected final String serviceUrl;
    private OkHttpClient downloader = new OkHttpClient();

    public GHMatrixAbstractRequester() {
        this("https://graphhopper.com/api/1/matrix");
    }

    public GHMatrixAbstractRequester(String serviceUrl) {
        if (serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl.substring(0, serviceUrl.length() - 1);
        }
        this.serviceUrl = serviceUrl;
        downloader.setConnectTimeout(5, TimeUnit.SECONDS);
    }

    public abstract MatrixResponse route(GHMRequest request, String key);

    public void setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
    }

    public OkHttpClient getDownloader() {
        return downloader;
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

    protected void fillResponseFromJson(GHMRequest request, List<String> outArraysList,
            MatrixResponse matrixResponse, JSONObject solution, boolean hasElevation) {
        int fromCount = request.getFromPoints().size(), toCount = request.getToPoints().size();
        if (outArraysList.contains("paths") && solution.has("paths")) {
            JSONArray pathArray = solution.getJSONArray("paths");
            for (int fromIndex = 0; fromIndex < fromCount; fromIndex++) {
                matrixResponse.newFromList();
                JSONArray fromArray = pathArray.getJSONArray(fromIndex);
                for (int toIndex = 0; toIndex < toCount; toIndex++) {
                    GHMResponse res = new GHMResponse(fromIndex, toIndex,
                            request.getFromPoints().get(fromIndex).equals(request.getToPoints().get(toIndex)));
                    AltResponse alt = new AltResponse();
                    res.addAlternative(alt);
                    GraphHopperWeb.readPath(alt, fromArray.getJSONObject(toIndex),
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
                    AltResponse alt = new AltResponse();
                    singleRsp.addAlternative(alt);
                    if (readWeights) {
                        alt.setRouteWeight(weightsFromArray.getDouble(toIndex));
                    }

                    if (readTimes) {
                        alt.setTime(timesFromArray.getLong(toIndex) * 1000);
                    }

                    if (readDistances) {
                        alt.setDistance(distancesFromArray.getDouble(toIndex));
                    }

                    matrixResponse.add(singleRsp);
                }
            }
        }
    }
}
