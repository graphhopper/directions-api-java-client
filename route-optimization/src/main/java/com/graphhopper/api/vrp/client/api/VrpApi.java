package com.graphhopper.api.vrp.client.api;

import com.graphhopper.api.vrp.client.ApiException;
import com.graphhopper.api.vrp.client.ApiClient;
import com.graphhopper.api.vrp.client.Configuration;
import com.graphhopper.api.vrp.client.Pair;
import com.graphhopper.api.vrp.client.TypeRef;
import com.graphhopper.api.vrp.client.model.Request;
import com.graphhopper.api.vrp.client.model.JobId;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class VrpApi {
  private ApiClient apiClient;

  public VrpApi() {
    this(Configuration.getDefaultApiClient());
  }

  public VrpApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Solves large routing problems
   * This endpoint solves large problems, i.e. traveling salesman or vehicle routing problems, and returns the solution.
   * @param key your API key
   * @param body Request object that contains the problem to be solved
   * @return JobId
   */
  public JobId postVrp (String key, Request body) throws ApiException {
    Object postBody = body;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'key' is set
     if (key == null) {
        throw new ApiException(400, "Missing the required parameter 'key' when calling postVrp");
     }
     
     // verify the required parameter 'body' is set
     if (body == null) {
        throw new ApiException(400, "Missing the required parameter 'body' when calling postVrp");
     }
     
    // create path and map variables
    String path = "/optimize".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    
    queryParams.addAll(apiClient.parameterToPairs("", "key", key));
    

    

    

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "api_key" };

    

    
    
    TypeRef returnType = new TypeRef<JobId>() {};
    return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
}
