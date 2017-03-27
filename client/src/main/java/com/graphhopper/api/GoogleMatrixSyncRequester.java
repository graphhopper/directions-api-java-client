package com.graphhopper.api;

import com.graphhopper.util.Helper;
import com.graphhopper.util.shapes.GHPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Peter Karich
 */
public class GoogleMatrixSyncRequester extends GHMatrixAbstractRequester {

    public GoogleMatrixSyncRequester() {
        super();
        initIgnore();
    }

    public GoogleMatrixSyncRequester(String serviceUrl) {
        super(serviceUrl);
        initIgnore();
    }

    private void initIgnore() {
        ignoreSet.add("mode");
        ignoreSet.add("units");
        ignoreSet.add("destinations");
        ignoreSet.add("origins");
        ignoreSet.add("mode");
    }

    @Override
    public MatrixResponse route(GHMRequest ghRequest) {
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
        String url = buildURL("", ghRequest);
        url += "&" + pointsStr + "&mode=" + ghRequest.getVehicle();

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

            pointsStr += encode(Helper.round6(p.lat) + "," + Helper.round6(p.lon));
        }
        return pointName + "=" + pointsStr;
    }

    public static void fillResponseFromGoogleJson(MatrixResponse matrixResponse, String responseAsString) {
        fillResponseFromGoogleJson(matrixResponse, new JSONObject(responseAsString));
    }

    private static void fillResponseFromGoogleJson(MatrixResponse matrixResponse, JSONObject responseJson) {
        String status = responseJson.getString("status");
        if ("OK".equals(status)) {
            if (!responseJson.has("rows")) {
                matrixResponse.addError(new RuntimeException("No 'rows' entry found in Google Matrix response. status:OK"));
                return;
            }

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
                        distances[toIndex] = Math.round(distance.getInt("value"));
                    } else {
                        matrixResponse.addError(new IllegalArgumentException("Cannot find route " + fromIndex + "->" + toIndex));
                    }
                }

                matrixResponse.setTimeRow(fromIndex, times);
                matrixResponse.setDistanceRow(fromIndex, distances);
            }
        } else if (responseJson.has("error_message")) {
            matrixResponse.addError(new RuntimeException(responseJson.getString("error_message")));
        } else {
            matrixResponse.addError(new RuntimeException("Something went wrong with Google Matrix response. status:" + status));
        }
    }
}
