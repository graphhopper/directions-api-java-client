package com.graphhopper.api;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Peter Karich
 */
public class GraphHopperWebTest {

    private final GraphHopperWeb gh = new GraphHopperWeb();

    @Before
    public void setUp() {
        gh.setKey(System.getProperty("graphhopper.key", ""));
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

    void isBetween(double from, double to, double expected) {
        assertTrue("expected value " + expected + " was smaller than limit " + from, expected >= from);
        assertTrue("expected value " + expected + " was bigger than limit " + to, expected <= to);
    }
}
