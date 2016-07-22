package com.graphhopper.api;

import static com.graphhopper.api.GHMatrixAbstractRequester.encode;
import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public class GHMatrixSyncRequester extends GHMatrixAbstractRequester {

    public GHMatrixSyncRequester() {
        super();
        initIgnore();
    }

    public GHMatrixSyncRequester(String serviceUrl, OkHttpClient client) {
        super(serviceUrl, client);
        initIgnore();
    }

    public GHMatrixSyncRequester(String serviceUrl) {
        super(serviceUrl, new OkHttpClient.Builder().
                connectTimeout(15, TimeUnit.SECONDS).
                readTimeout(15, TimeUnit.SECONDS).build());
        initIgnore();
    }

    private void initIgnore() {
        ignoreSet.add("vehicle");
        ignoreSet.add("point");
        ignoreSet.add("from_point");
        ignoreSet.add("to_point");
        ignoreSet.add("add_array");
    }

    @Override
    public MatrixResponse route(GHMRequest ghRequest) {
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

        String url = buildURL("", ghRequest);
        url += "&" + pointsStr + "&" + outArrayStr + "&vehicle=" + ghRequest.getVehicle();

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
}
