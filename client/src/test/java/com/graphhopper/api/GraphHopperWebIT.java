package com.graphhopper.api;

import com.graphhopper.PathWrapper;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.RoundaboutInstruction;
import com.graphhopper.util.exceptions.PointNotFoundException;
import com.graphhopper.util.exceptions.PointOutOfBoundsException;
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
        String key = System.getProperty("graphhopper.key", "369dc982-86a6-484e-95ad-669331663ca4");
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
        req.getHints().put("calc_points", true);
        GHResponse res = gh.route(req);
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        PathWrapper alt = res.getBest();
        isBetween(200, 250, alt.getPoints().size());
        isBetween(11000, 12000, alt.getDistance());

        // change vehicle
        res = gh.route(new GHRequest(49.6724, 11.3494, 49.6550, 11.4180).
                setVehicle("bike"));
        alt = res.getBest();
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        isBetween(9000, 10000, alt.getDistance());
    }

    @Test
    public void testNoPoints() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(49.6724, 11.3494)).
                addPoint(new GHPoint(49.6550, 11.4180));

        req.getHints().put("instructions", false);
        req.getHints().put("calc_points", false);
        GHResponse res = gh.route(req);
        assertFalse("errors:" + res.getErrors().toString(), res.hasErrors());
        PathWrapper alt = res.getBest();
        assertEquals(0, alt.getPoints().size());
        isBetween(11000, 12000, alt.getDistance());
    }

    @Test
    public void readRoundabout() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(52.261434, 13.485718)).
                addPoint(new GHPoint(52.399067, 13.469238));

        GHResponse res = gh.route(req);
        int counter = 0;
        for (Instruction i : res.getBest().getInstructions()) {
            if (i instanceof RoundaboutInstruction) {
                counter++;
                RoundaboutInstruction ri = (RoundaboutInstruction) i;
                assertEquals("turn_angle was incorrect:" + ri.getTurnAngle(), -1.5, ri.getTurnAngle(), 0.1);
            }
        }
        assertTrue("no roundabout in route?", counter > 0);
    }

    @Test
    public void testCannotFindPointException() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(-4.214943, -130.078125)).
                addPoint(new GHPoint(39.909736, -91.054687));

        GHResponse res = gh.route(req);
        assertTrue("no erros found?", res.hasErrors());
        assertTrue(res.getErrors().get(0) instanceof PointNotFoundException);
    }


    @Test
    public void testOutOfBoundsException() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(-400.214943, -130.078125)).
                addPoint(new GHPoint(39.909736, -91.054687));

        GHResponse res = gh.route(req);
        assertTrue("no erros found?", res.hasErrors());
        assertTrue(res.getErrors().get(0) instanceof PointOutOfBoundsException);
    }

    @Test
    public void readFinishInstruction() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(52.261434, 13.485718)).
                addPoint(new GHPoint(52.399067, 13.469238));

        GHResponse res = gh.route(req);
        InstructionList instructions = res.getBest().getInstructions();
        String finishInstructionName = instructions.get(instructions.getSize()-1).getName();
        assertEquals("Finish!", finishInstructionName);
    }

    @Test
    public void testSimpleExport() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(49.6724, 11.3494)).
                addPoint(new GHPoint(49.6550, 11.4180));
        req.getHints().put("elevation", false);
        req.getHints().put("instructions", true);
        req.getHints().put("calc_points", true);
        req.getHints().put("type", "gpx");
        String res = gh.export(req);
        assertTrue(res.contains("<gpx"));
        assertTrue(res.contains("<rtept lat="));
        assertTrue(res.contains("<trk><name>GraphHopper Track</name><trkseg>"));
        assertTrue(res.endsWith("</gpx>"));
    }

    @Test
    public void testExportWithoutTrack() {
        GHRequest req = new GHRequest().
                addPoint(new GHPoint(49.6724, 11.3494)).
                addPoint(new GHPoint(49.6550, 11.4180));
        req.getHints().put("elevation", false);
        req.getHints().put("instructions", true);
        req.getHints().put("calc_points", true);
        req.getHints().put("type", "gpx");
        req.getHints().put("gpx.track", "false");
        String res = gh.export(req);
        assertTrue(res.contains("<gpx"));
        assertTrue(res.contains("<rtept lat="));
        assertTrue(!res.contains("<trk><name>GraphHopper Track</name><trkseg>"));
        assertTrue(res.endsWith("</gpx>"));
    }

    void isBetween(double from, double to, double expected) {
        assertTrue("expected value " + expected + " was smaller than limit " + from, expected >= from);
        assertTrue("expected value " + expected + " was bigger than limit " + to, expected <= to);
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
        assertEquals(1160, res.getWeight(1, 2), 5);

        req = AbstractGHMatrixWebTester.createRequest();
        req.addOutArray("weights");
        req.addOutArray("distances");
        res = ghMatrix.route(req);

        assertEquals(9637, res.getDistance(1, 2), 5);
        assertEquals(1160, res.getWeight(1, 2), 5);
    }
}
