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
import com.graphhopper.directions.api.client.model.JobId;
import com.graphhopper.directions.api.client.model.Request;
import org.junit.Test;
import org.junit.Ignore;

/**
 * API tests for VrpApi
 */
@Ignore
public class VrpApiTest {

    private final VrpApi api = new VrpApi();


    /**
     * Solves vehicle routing problems
     * <p>
     * This endpoint for solving vehicle routing problems, i.e. traveling salesman or vehicle routing problems, and returns the solution.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postVrpTest() throws ApiException {
        String key = null;
        Request body = null;
        JobId response = api.postVrp(key, body);

        // TODO: test validations
    }

}
