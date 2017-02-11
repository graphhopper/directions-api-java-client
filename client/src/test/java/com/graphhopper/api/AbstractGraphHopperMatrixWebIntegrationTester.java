package com.graphhopper.api;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Peter Karich
 */
public abstract class AbstractGraphHopperMatrixWebIntegrationTester {

    protected GraphHopperMatrixWeb ghMatrix;

    abstract GraphHopperMatrixWeb createMatrixWeb();

    @Before
    public void setUp() {
        String key = System.getProperty("graphhopper.key", GraphHopperWebIT.KEY);
        ghMatrix = createMatrixWeb();
        ghMatrix.setKey(key);
    }

    @Test
    public void testMatrix() {
        GHMRequest req = AbstractGHMatrixWebTester.createRequest();
        MatrixResponse res = ghMatrix.route(req);

        // no distances available
        try {
            assertEquals(0, res.getDistance(1, 2), 1);
            assertTrue(false);
        } catch (Exception ex) {
        }

        // ... only weight:
        assertEquals(1680, res.getWeight(1, 2), 5);

        req = AbstractGHMatrixWebTester.createRequest();
        req.addOutArray("weights");
        req.addOutArray("distances");
        res = ghMatrix.route(req);

        assertEquals(9637, res.getDistance(1, 2), 5);
        assertEquals(1680, res.getWeight(1, 2), 5);
    }

    @Test
    public void testBikeMatrix() {
        GHMRequest req = AbstractGHMatrixWebTester.createRequest();
        req.setVehicle("bike");
        req.addOutArray("times");

        MatrixResponse res = ghMatrix.route(req);
        assertEquals(2310, res.getTime(1, 2) / 1000, 20);
    }
}
