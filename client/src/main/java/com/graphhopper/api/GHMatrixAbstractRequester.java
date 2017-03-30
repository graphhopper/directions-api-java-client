package com.graphhopper.api;

import static com.graphhopper.api.GraphHopperMatrixWeb.KEY;
import static com.graphhopper.api.GraphHopperMatrixWeb.MT_JSON;
import static com.graphhopper.api.GraphHopperMatrixWeb.SERVICE_URL;

import com.graphhopper.util.Helper;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Peter Karich
 */
public abstract class GHMatrixAbstractRequester {

    protected final Set<String> ignoreSet = new HashSet<String>(10);
    protected final String serviceUrl;
    private OkHttpClient downloader;

    public GHMatrixAbstractRequester() {
        this("https://graphhopper.com/api/1/matrix");
    }

    public GHMatrixAbstractRequester(String serviceUrl) {
        this(serviceUrl, new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).build());
    }

    public GHMatrixAbstractRequester(String serviceUrl, OkHttpClient downloader) {
        if (serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl.substring(0, serviceUrl.length() - 1);
        }
        this.downloader = downloader;
        this.serviceUrl = serviceUrl;

        ignoreSet.add("key");
        ignoreSet.add("service_url");
    }

    public abstract MatrixResponse route(GHMRequest request);

    public GHMatrixAbstractRequester setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
        return this;
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

    public List<Throwable> readUsableEntityError(List<String> outArraysList, JSONObject solution) {
        boolean readWeights = outArraysList.contains("weights") && solution.has("weights");
        boolean readDistances = outArraysList.contains("distances") && solution.has("distances");
        boolean readTimes = outArraysList.contains("times") && solution.has("times");

        if (!readWeights && !readDistances && !readTimes) {
            return Collections.<Throwable>singletonList(new RuntimeException("Cannot find usable entity like weights, distances or times in JSON"));
        } else {
            return Collections.emptyList();
        }
    }

    public static void fillResponseFromJson(MatrixResponse matrixResponse, String responseAsString) {
        fillResponseFromJson(matrixResponse, new JSONObject(responseAsString));
    }

    protected static void fillResponseFromJson(MatrixResponse matrixResponse, JSONObject solution) {
        final boolean readWeights = solution.has("weights");
        final boolean readDistances = solution.has("distances");
        final boolean readTimes = solution.has("times");

        int fromCount = 0;
        JSONArray weightsArray = null;
        if (readWeights) {
            weightsArray = solution.getJSONArray("weights");
            fromCount = checkArraySizes("weights", weightsArray.length());
        }
        JSONArray timesArray = null;
        if (readTimes) {
            timesArray = solution.getJSONArray("times");
            fromCount = checkArraySizes("times", timesArray.length(), weightsArray);
        }
        JSONArray distancesArray = null;
        if (readDistances) {
            distancesArray = solution.getJSONArray("distances");
            fromCount = checkArraySizes("distances", distancesArray.length(), weightsArray, timesArray);
        }

        for (int fromIndex = 0; fromIndex < fromCount; fromIndex++) {
            int toCount = 0;
            JSONArray weightsFromArray = null;
            double[] weights = null;
            if (readWeights) {
                weightsFromArray = weightsArray.getJSONArray(fromIndex);
                weights = new double[weightsFromArray.length()];
                toCount = checkArraySizes("weights", weightsFromArray.length());
            }

            JSONArray timesFromArray = null;
            long[] times = null;
            if (readTimes) {
                timesFromArray = timesArray.getJSONArray(fromIndex);
                times = new long[timesFromArray.length()];
                toCount = checkArraySizes("times", timesFromArray.length(), weightsFromArray);
            }

            JSONArray distancesFromArray = null;
            int[] distances = null;
            if (readDistances) {
                distancesFromArray = distancesArray.getJSONArray(fromIndex);
                distances = new int[distancesFromArray.length()];
                toCount = checkArraySizes("distances", distancesFromArray.length(), weightsFromArray, timesFromArray);
            }

            for (int toIndex = 0; toIndex < toCount; toIndex++) {
                if (readWeights) {
                    weights[toIndex] = weightsFromArray.getDouble(toIndex);
                }

                if (readTimes) {
                    times[toIndex] = timesFromArray.getLong(toIndex) * 1000;
                }

                if (readDistances) {
                    distances[toIndex] = (int) Math.round(distancesFromArray.getDouble(toIndex));
                }
            }

            if (readWeights) {
                matrixResponse.setWeightRow(fromIndex, weights);
            }

            if (readTimes) {
                matrixResponse.setTimeRow(fromIndex, times);
            }

            if (readDistances) {
                matrixResponse.setDistanceRow(fromIndex, distances);
            }
        }
    }

    private static int checkArraySizes(String msg, int len, JSONArray... arrays) {
        for (JSONArray other : arrays) {
            if (len <= 0)
                throw new IllegalArgumentException("Size " + len + " of '" + msg + "' array is too small");

            if (other != null && len != other.length())
                throw new IllegalArgumentException("Size " + len + " of '" + msg + "' array is has to be equal to other arrays but wasn't");
        }
        return len;
    }

    protected String buildURLNoHints(String path, GHMRequest ghRequest) {
        // allow per request service URLs
        String tmpServiceURL = ghRequest.getHints().get(SERVICE_URL, serviceUrl);
        String url = tmpServiceURL;
        url += path + "?";

        String key = ghRequest.getHints().get(KEY, "");
        if (!Helper.isEmpty(key)) {
            url += "key=" + key;
        }
        return url;
    }

    protected String buildURL(String path, GHMRequest ghRequest) {
        String url = buildURLNoHints(path, ghRequest);
        for (Map.Entry<String, String> entry : ghRequest.getHints().toMap().entrySet()) {
            if (ignoreSet.contains(entry.getKey())) {
                continue;
            }

            url += "&" + encode(entry.getKey()) + "=" + encode(entry.getValue());
        }
        return url;
    }

    protected static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception ex) {
            return str;
        }
    }
}
