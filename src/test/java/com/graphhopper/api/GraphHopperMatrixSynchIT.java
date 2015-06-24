package com.graphhopper.api;

/**
 *
 * @author Peter Karich
 */
public class GraphHopperMatrixSynchIT extends AbstractGraphHopperMatrixWebIntegrationTester {

    @Override
    GraphHopperMatrixWeb createMatrixWeb() {
        return new GraphHopperMatrixWeb(new GHMatrixSyncRequester(GraphHopperMatrixWeb.SERVICE_URL));
    }
}
