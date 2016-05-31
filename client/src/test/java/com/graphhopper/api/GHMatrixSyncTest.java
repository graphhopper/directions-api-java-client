package com.graphhopper.api;

import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Peter Karich
 */
public class GHMatrixSyncTest extends AbstractGHMatrixWebTester {

    @Override
    GraphHopperMatrixWeb createMatrixClient(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        if (json.has("solution")) {
            json = json.getJSONObject("solution");
        }
        final String finalJsonStr = json.toString();
        return new GraphHopperMatrixWeb(new GHMatrixSyncRequester("") {

            @Override
            protected String postJson(String url, JSONObject data) throws IOException {
                return "{\"job_id\": \"1\"}";
            }

            @Override
            protected String getJson(String url) throws IOException {
                return finalJsonStr;
            }
        });
    }
}
