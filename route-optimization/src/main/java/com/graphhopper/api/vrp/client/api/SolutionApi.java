package com.graphhopper.api.vrp.client.api;

import com.graphhopper.api.vrp.client.ApiException;
import com.graphhopper.api.vrp.client.ApiClient;
import com.graphhopper.api.vrp.client.Configuration;
import com.graphhopper.api.vrp.client.Pair;
import com.graphhopper.api.vrp.client.TypeRef;
import com.graphhopper.api.vrp.client.model.Response;

import java.util.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class SolutionApi {
  private ApiClient apiClient;

  public SolutionApi() {
    this(Configuration.getDefaultApiClient());
  }

  public SolutionApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Return the solution associated to the jobId
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.
   * @param key your API key
   * @param jobId Request solution with jobId
   * @return Response
   */
  public Response getSolution (String key, String jobId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'key' is set
     if (key == null) {
        throw new ApiException(400, "Missing the required parameter 'key' when calling getSolution");
     }
     
     // verify the required parameter 'jobId' is set
     if (jobId == null) {
        throw new ApiException(400, "Missing the required parameter 'jobId' when calling getSolution");
     }
     
    // create path and map variables
    String path = "/solution/{jobId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "jobId" + "\\}", apiClient.escapeString(jobId.toString()));

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

    

    
    
    TypeRef returnType = new TypeRef<Response>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    
    


  }
  
}
