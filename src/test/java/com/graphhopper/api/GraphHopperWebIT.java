package com.graphhopper.api;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.RoundaboutInstruction;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Peter Karich
 */
public class GraphHopperWebIT {

    private final GraphHopperWeb gh = new GraphHopperWeb();
    private final GraphHopperMatrixWeb ghMatrix = new GraphHopperMatrixWeb();

    @Before
    public void setUp() {
        String key = System.getProperty("graphhopper.key", "");
        gh.setKey(key);
        ghMatrix.setKey(key);
    }

    @Test
    public void testSimpleRoute() {
        // https://graphhopper.com/maps/?point=49.6724%2C11.3494&point=49.655%2C11.418
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(49.6724, 11.3494)).
                addPoint(new GHPoint(49.6550, 11.4180));
        req.getHints().put("elevation", false);
        req.getHints().put("instructions", true);
        req.getHints().put("calcPoints", true);
        GHResponse res = gh.route(req);
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        isBetween(200, 250, res.getPoints().size());
        isBetween(11000, 12000, res.getDistance());

        // change vehicle
        res = gh.route(new GHRequest(49.6724, 11.3494, 49.6550, 11.4180).
                setVehicle("bike"));
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        isBetween(9000, 10000, res.getDistance());
    }

    @Test
    public void testNoPoints() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(49.6724, 11.3494)).
                addPoint(new GHPoint(49.6550, 11.4180));

        req.getHints().put("instructions", false);
        req.getHints().put("calcPoints", false);
        GHResponse res = gh.route(req);
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        assertEquals(0, res.getPoints().size());
        isBetween(11000, 12000, res.getDistance());
    }

    @Test
    public void readRoundabout() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(52.261434, 13.485718)).
                addPoint(new GHPoint(52.399067, 13.469238));

        GHResponse res = gh.route(req);
        int counter = 0;
        for (Instruction i : res.getInstructions()) {
            if (i instanceof RoundaboutInstruction) {
                counter++;
                RoundaboutInstruction ri = (RoundaboutInstruction) i;
                assertEquals("turn_angle was incorrect:" + ri.getRadian(), -1.5, ri.getRadian(), 0.1);
            }
        }
        assertTrue("no roundabout in route?", counter > 0);
    }

    void isBetween(double from, double to, double expected) {
        assertTrue("expected value " + expected + " was smaller than limit " + from, expected >= from);
        assertTrue("expected value " + expected + " was bigger than limit " + to, expected <= to);
    }

    @Test
    public void testMatrix() {
        GHMRequest req = GraphHopperMatrixWebTest.createRequest();
        MatrixResponse res = ghMatrix.route(req);

        // no distances available
        assertEquals(0, res.get(1, 2).getDistance(), .1);
        // ... only weight:
        assertEquals(807.167, res.get(1, 2).getRouteWeight(), .1);

        req = GraphHopperMatrixWebTest.createRequest();
        req.addOutArray("weights");
        req.addOutArray("distances");
        res = ghMatrix.route(req);

        assertEquals(9734., res.get(1, 2).getDistance(), .1);
        assertEquals(807.167, res.get(1, 2).getRouteWeight(), .1);
    }
}
