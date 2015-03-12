/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graphhopper.api;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
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

        gh.load("https://graphhopper.com/api/1/route");
    }
    
    @Test
    public void testSimpleRoute() {
        // https://graphhopper.com/maps/?point=49.6724%2C11.3494&point=49.655%2C11.418
        GHResponse ph = gh.route(new GHRequest(49.6724, 11.3494, 49.6550, 11.4180));
        assertFalse("errors:" + ph.getErrors().toString(), ph.hasErrors());
        assertTrue(ph.getDistance() > 11000);
        assertTrue(ph.getDistance() < 12000);
        
        ph = gh.route(new GHRequest(49.6724, 11.3494, 49.6550, 11.4180).
                setVehicle("bike"));
        assertFalse("errors:" + ph.getErrors().toString(), ph.hasErrors());
        assertTrue(ph.getDistance() > 9000);
        assertTrue(ph.getDistance() < 10000);
    }   
}
