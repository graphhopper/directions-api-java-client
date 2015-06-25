package com.graphhopper.api;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Peter Karich
 */
public abstract class AbstractGraphHopperMatrixWebIntegrationTester {

    private GraphHopperMatrixWeb ghMatrix;
    
    abstract GraphHopperMatrixWeb createMatrixWeb();
    
    @Before
    public void setUp() {
        String key = System.getProperty("graphhopper.key", "");
        ghMatrix = createMatrixWeb();
        ghMatrix.setKey(key);
    }

    @Test
    public void testMatrix() {
        GHMRequest req = AbstractGHMatrixWebTester.createRequest();
        MatrixResponse res = ghMatrix.route(req);

        // no distances available
        assertEquals(0, res.get(1, 2).getDistance(), .1);
        // ... only weight:
        assertEquals(807.167, res.get(1, 2).getRouteWeight(), .1);

        req = AbstractGHMatrixWebTester.createRequest();
        req.addOutArray("weights");
        req.addOutArray("distances");
        res = ghMatrix.route(req);

        assertEquals(9734., res.get(1, 2).getDistance(), 5);
        assertEquals(807.167, res.get(1, 2).getRouteWeight(), 5);
    }
}
