/*
 * GraphHopper Directions API
 * You use the GraphHopper Directions API to add route planning, navigation and route optimization to your software. E.g. the Routing API has turn instructions and elevation data and the Route Optimization API solves your logistic problems and supports various constraints like time window and capacity restrictions. Also it is possible to get all distances between all locations with our fast Matrix API.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.graphhopper.directions.api.client.api;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.model.Response;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SolutionApi
 */
@Ignore
public class SolutionApiTest {

    private final SolutionApi api = new SolutionApi();

    
    /**
     * Return the solution associated to the jobId
     *
     * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSolutionTest() throws ApiException {
        String key = null;
        String jobId = null;
        Response response = api.getSolution(key, jobId);

        // TODO: test validations
    }
    
}
