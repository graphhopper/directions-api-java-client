package com.graphhopper.routeopt.client.api;

import com.graphhopper.routeopt.client.ApiCallback;
import com.graphhopper.routeopt.client.ApiClient;
import com.graphhopper.routeopt.client.ApiException;
import com.graphhopper.routeopt.client.Configuration;
import com.graphhopper.routeopt.client.Pair;

import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.Call;

import com.graphhopper.routeopt.client.model.Response;

import java.lang.reflect.Type;
import java.util.*;

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

  
  /* Build call for getSolution */
  private Call getSolutionCall(String key, String jobId) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'key' is set
    if (key == null) {
       throw new ApiException("Missing the required parameter 'key' when calling getSolution(Async)");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
       throw new ApiException("Missing the required parameter 'jobId' when calling getSolution(Async)");
    }
    

    // create path and map variables
    String path = "/solution/{jobId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "jobId" + "\\}", apiClient.escapeString(jobId.toString()));

    List<Pair> queryParams = new ArrayList<Pair>();
    if (key != null)
      queryParams.addAll(apiClient.parameterToPairs("", "key", key));

    Map<String, String> headerParams = new HashMap<String, String>();

    Map<String, Object> formParams = new HashMap<String, Object>();

    final String[] accepts = {
      "application/json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);
    if (accept != null) headerParams.put("Accept", accept);

    final String[] contentTypes = {
      "application/json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);
    headerParams.put("Content-Type", contentType);

    String[] authNames = new String[] { "api_key" };
    return apiClient.buildCall(path, "GET", queryParams, postBody, headerParams, formParams, authNames);
  }

  /**
   * Return the solution associated to the jobId
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.
   * @param key your API key
   * @param jobId Request solution with jobId
   * @return Response
   */
  public Response getSolution(String key, String jobId) throws ApiException {
    Call call = getSolutionCall(key, jobId);
    Type returnType = new TypeToken<Response>(){}.getType();
    return apiClient.execute(call, returnType);
  }

  /**
   * Return the solution associated to the jobId (asynchronously)
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.
   * @param key your API key
   * @param jobId Request solution with jobId
   * @param callback The callback to be executed when the API call finishes
   * @return The request call
   */
  public Call getSolutionAsync(String key, String jobId, ApiCallback<Response> callback) throws ApiException {
    Call call = getSolutionCall(key, jobId);
    Type returnType = new TypeToken<Response>(){}.getType();
    apiClient.executeAsync(call, returnType, callback);
    return call;
  }
  
}
