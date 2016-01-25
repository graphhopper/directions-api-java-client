package com.graphhopper.api;

import com.graphhopper.util.shapes.GHPoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Peter Karich
 */
public abstract class AbstractGHMatrixWebTester {

    abstract GraphHopperMatrixWeb createMatrixClient(String json);

    public static GHMRequest createRequest() {
        GHMRequest req = new GHMRequest();
        req.addPoint(new GHPoint(51.534377, -0.087891));
        req.addPoint(new GHPoint(51.467697, -0.090637));
        req.addPoint(new GHPoint(51.521241, -0.171833));
        req.addPoint(new GHPoint(51.473685, -0.211487));
        return req;
    }

    @Test
    public void testReadingMatrixWithError() throws IOException {
        String ghMatrix = readFile(new InputStreamReader(getClass().getResourceAsStream("matrix_error.json")));
        GraphHopperMatrixWeb matrixWeb = createMatrixClient(ghMatrix);

        GHMRequest req = createRequest();
        MatrixResponse rsp = matrixWeb.route(req);

        assertTrue(rsp.hasErrors());
        assertEquals(2, rsp.getErrors().size());
    }

    @Test
    public void testReadingWeights() throws IOException {
        String ghMatrix = readFile(new InputStreamReader(getClass().getResourceAsStream("matrix.json")));
        GraphHopperMatrixWeb matrixWeb = createMatrixClient(ghMatrix);

        GHMRequest req = createRequest();
        MatrixResponse rsp = matrixWeb.route(req);
        assertFalse(rsp.hasErrors());

        assertEquals(0., rsp.get(0, 1).getFirst().getDistance(), .1);
    }

    @Test
    public void testReadingWeights_TimesAndDistances() throws IOException {
        String ghMatrix = readFile(new InputStreamReader(getClass().getResourceAsStream("matrix.json")));
        GraphHopperMatrixWeb matrixWeb = createMatrixClient(ghMatrix);

        GHMRequest req = createRequest();
        req.addOutArray("weights");
        req.addOutArray("distances");
        req.addOutArray("times");
        MatrixResponse rsp = matrixWeb.route(req);

        assertFalse(rsp.hasErrors());

        assertEquals(9475., rsp.get(0, 1).getFirst().getDistance(), .1);
        assertEquals(9734., rsp.get(1, 2).getFirst().getDistance(), .1);
        assertEquals(0., rsp.get(1, 1).getFirst().getDistance(), .1);

        assertEquals(885.867, rsp.get(0, 1).getFirst().getRouteWeight(), .1);
        assertEquals(807.167, rsp.get(1, 2).getFirst().getRouteWeight(), .1);
        assertEquals(0., rsp.get(1, 1).getFirst().getRouteWeight(), .1);

        assertEquals(886, rsp.get(0, 1).getFirst().getTime() / 1000);
    }

    @Test
    public void testReadingPaths() throws IOException {
        String ghMatrix = readFile(new InputStreamReader(getClass().getResourceAsStream("matrix.json")));
        GraphHopperMatrixWeb matrixWeb = createMatrixClient(ghMatrix);

        GHMRequest req = createRequest();
        req.addOutArray("paths");
        req.addOutArray("distances");
        MatrixResponse rsp = matrixWeb.route(req);

        assertFalse(rsp.hasErrors());

        // for paths we store more precise data:
        assertEquals(9474.564, rsp.get(0, 1).getFirst().getDistance(), .1);
        assertEquals(9733.831, rsp.get(1, 2).getFirst().getDistance(), .1);
        assertEquals(0., rsp.get(1, 1).getFirst().getDistance(), .1);

        assertEquals(170, rsp.get(0, 1).getFirst().getPoints().size(), .1);
        assertEquals(174, rsp.get(1, 2).getFirst().getPoints().size(), .1);

        assertEquals(885, rsp.get(0, 1).getFirst().getTime() / 1000);
    }

    public static String readFile(Reader simpleReader) throws IOException {
        try (BufferedReader reader = new BufferedReader(simpleReader)) {
            String res = "";
            String line;
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            return res;
        }
    }
}
