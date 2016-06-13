package com.graphhopper.api;

import com.graphhopper.util.StopWatch;
import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public class GHMatrixSyncRequester extends GHMatrixAbstractRequester {

    public GHMatrixSyncRequester() {
        super();
    }

    public GHMatrixSyncRequester(String serviceUrl) {
        super(serviceUrl);
    }

    @Override
    public MatrixResponse route(GHMRequest ghRequest, String key) {
        String pointsStr;
        if (ghRequest.identicalLists) {
            pointsStr = createPointQuery(ghRequest.getFromPoints(), "point");
        } else {
            pointsStr = createPointQuery(ghRequest.getFromPoints(), "from_point");
            pointsStr += "&" + createPointQuery(ghRequest.getToPoints(), "to_point");
        }

        String outArrayStr = "";
        List<String> outArraysList = new ArrayList<>(ghRequest.getOutArrays());
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

        String tmpServiceURL = ghRequest.getHints().get("service_url", serviceUrl);
        String url = tmpServiceURL;
        if (!url.contains("?")) {
            url += "?";
        }
        url += pointsStr + "&" + outArrayStr + "&vehicle=" + ghRequest.getVehicle() + "&key=" + key;

        boolean withTimes = outArraysList.contains("times");
        boolean withDistances = outArraysList.contains("distances");
        boolean withWeights = outArraysList.contains("weights");
        MatrixResponse matrixResponse = new MatrixResponse(
                ghRequest.getFromPoints().size(),
                ghRequest.getToPoints().size(), withTimes, withDistances, withWeights);

        try {
            String str = getJson(url);
            JSONObject getResponseJson = null;
            try {
                getResponseJson = new JSONObject(str);
            } catch (Exception ex) {
                throw new RuntimeException("Cannot parse json " + str + " from " + url);
            }

            matrixResponse.addErrors(GraphHopperWeb.readErrors(getResponseJson));
            if (!matrixResponse.hasErrors()) {
                fillResponseFromJson(ghRequest, outArraysList,
                        matrixResponse, getResponseJson, hasElevation);
            }

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
}
