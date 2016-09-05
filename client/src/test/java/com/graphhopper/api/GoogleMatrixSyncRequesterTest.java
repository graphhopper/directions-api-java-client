package com.graphhopper.api;

import static com.graphhopper.api.AbstractGHMatrixWebTester.createRequest;
import com.graphhopper.util.shapes.GHPoint;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter Karich
 */
public class GoogleMatrixSyncRequesterTest {

    GraphHopperMatrixWeb createMatrixClient(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        final String finalJsonStr = json.toString();
        return new GraphHopperMatrixWeb(new GoogleMatrixSyncRequester("") {

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

    @Test
    public void testMatrix() throws IOException {
        GHMRequest req = createRequest();

        GraphHopperMatrixWeb matrix = createMatrixClient(AbstractGHMatrixWebTester.readFile(
                new InputStreamReader(getClass().getResourceAsStream("google-matrix1.json"))));
        MatrixResponse rsp = matrix.route(req);

        assertEquals(712692, rsp.getDistance(0, 1), .1);
        assertEquals(25995, rsp.getTime(0, 1) / 1000);

        assertEquals(806813, rsp.getDistance(1, 2), .1);
        assertEquals(28737, rsp.getTime(1, 2) / 1000);
    }

    @Test
    public void testMatrixWithError() throws IOException {
        GHMRequest req = createRequest();

        GraphHopperMatrixWeb matrix = createMatrixClient(AbstractGHMatrixWebTester.readFile(
                new InputStreamReader(getClass().getResourceAsStream("google-error1.json"))));
        MatrixResponse rsp = matrix.route(req);
        assertTrue(rsp.hasErrors());
    }
}
