package com.graphhopper.api;

import com.graphhopper.util.Helper;
import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public class GoogleMatrixSyncRequester extends GHMatrixAbstractRequester {

    private final Set<String> ignoreSet = new HashSet<String>(10);

    public GoogleMatrixSyncRequester() {
        super();
    }

    public GoogleMatrixSyncRequester(String serviceUrl) {
        super(serviceUrl);

        ignoreSet.add("mode");
        ignoreSet.add("key");
        ignoreSet.add("units");
        ignoreSet.add("destinations");
        ignoreSet.add("origins");
        ignoreSet.add("mode");
    }

    @Override
    public MatrixResponse route(GHMRequest ghRequest, String key) {
        String pointsStr;

        pointsStr = createGoogleQuery(ghRequest.getFromPoints(), "origins");
        pointsStr += "&" + createGoogleQuery(ghRequest.getToPoints(), "destinations");

        List<String> outArraysList = new ArrayList<>(ghRequest.getOutArrays());
        if (outArraysList.isEmpty()) {
            // different default as google does not support weights
            outArraysList.add("distances");
            outArraysList.add("times");
        }

        // do not do the mapping here!
        // bicycling -> bike, car -> car, walking -> foot
        //
        // allow per request service URLs
        String tmpServiceURL = ghRequest.getHints().get("service_url", serviceUrl);
        String url = tmpServiceURL;
        if (!url.contains("?")) {
            url += "?";
        }
        url += pointsStr + "&mode=" + ghRequest.getVehicle();

        if (!Helper.isEmpty(key)) {
            url += "&key=" + key;
        }

        for (Entry<String, String> entry : ghRequest.getHints().toMap().entrySet()) {
            if (ignoreSet.contains(entry.getKey())) {
                continue;
            }

            url += "&" + entry.getKey() + "=" + encode(entry.getValue());
        }

        boolean withTimes = outArraysList.contains("times");
        boolean withDistances = outArraysList.contains("distances");
        boolean withWeights = outArraysList.contains("weights");
        if (withWeights) {
            throw new UnsupportedOperationException("Google Matrix API does not include weights");
        }

        MatrixResponse matrixResponse = new MatrixResponse(
                ghRequest.getFromPoints().size(),
                ghRequest.getToPoints().size(), withTimes, withDistances, false);

        try {
            String str = getJson(url);
            JSONObject getResponseJson = null;
            try {
                getResponseJson = new JSONObject(str);
            } catch (Exception ex) {
                throw new RuntimeException("Cannot parse json " + str + " from " + url);
            }

            fillResponseFromGoogleJson(matrixResponse, getResponseJson);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return matrixResponse;
    }

    private String createGoogleQuery(List<GHPoint> list, String pointName) {
        String pointsStr = "";
        for (GHPoint p : list) {
            if (!pointsStr.isEmpty()) {
                pointsStr += "|";
            }

            pointsStr += encode(p.lat + "," + p.lon);
        }
        return pointName + "=" + pointsStr;
    }

    public String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception ex) {
            return str;
        }
    }

    private void fillResponseFromGoogleJson(MatrixResponse matrixResponse, JSONObject responseJson) {
        JSONArray rows = responseJson.getJSONArray("rows");
        int fromCount = rows.length();

        for (int fromIndex = 0; fromIndex < fromCount; fromIndex++) {
            JSONObject elementsObj = rows.getJSONObject(fromIndex);
            JSONArray elements = elementsObj.getJSONArray("elements");
            int toCount = elements.length();
            long[] times = new long[toCount];
            int[] distances = new int[toCount];

            for (int toIndex = 0; toIndex < toCount; toIndex++) {
                JSONObject element = elements.getJSONObject(toIndex);

                if ("OK".equals(element.getString("status"))) {

                    JSONObject distance = element.getJSONObject("distance");
                    JSONObject duration = element.getJSONObject("duration");

                    times[toIndex] = duration.getInt("value") * 1000;
                    distances[toIndex] = (int) Math.round(distance.getInt("value"));
                } else {
                    matrixResponse.addError(new IllegalArgumentException("Cannot find route " + fromIndex + "->" + toIndex));
                }
            }

            matrixResponse.setTimeRow(fromIndex, times);
            matrixResponse.setDistanceRow(fromIndex, distances);
        }
    }
}
