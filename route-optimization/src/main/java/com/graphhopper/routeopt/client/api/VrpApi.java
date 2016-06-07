package com.graphhopper.routeopt.client.api;

import com.graphhopper.routeopt.client.ApiCallback;
import com.graphhopper.routeopt.client.ApiClient;
import com.graphhopper.routeopt.client.ApiException;
import com.graphhopper.routeopt.client.ApiResponse;
import com.graphhopper.routeopt.client.Configuration;
import com.graphhopper.routeopt.client.Pair;
import com.graphhopper.routeopt.client.ProgressRequestBody;
import com.graphhopper.routeopt.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

import com.graphhopper.routeopt.client.model.JobId;
import com.graphhopper.routeopt.client.model.Request;

import java.lang.reflect.Type;
import java.util.*;

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

  
  /* Build call for postVrp */
  private Call postVrpCall(String key, Request body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
    Object postBody = body;
    
    // verify the required parameter 'key' is set
    if (key == null) {
       throw new ApiException("Missing the required parameter 'key' when calling postVrp(Async)");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
       throw new ApiException("Missing the required parameter 'body' when calling postVrp(Async)");
    }
    

    // create path and map variables
    String path = "/optimize".replaceAll("\\{format\\}","json");

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

    if(progressListener != null) {
      apiClient.getHttpClient().networkInterceptors().add(new Interceptor() {
      @Override
      public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                .build();
        }
      });
    }

    String[] authNames = new String[] { "api_key" };
    return apiClient.buildCall(path, "POST", queryParams, postBody, headerParams, formParams, authNames, progressRequestListener);
  }

  /**
   * Solves large routing problems
   * This endpoint solves large problems, i.e. traveling salesman or vehicle routing problems, and returns the solution.
   * @param key your API key
   * @param body Request object that contains the problem to be solved
   * @return JobId
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public JobId postVrp(String key, Request body) throws ApiException {
    ApiResponse<JobId> resp = postVrpWithHttpInfo(key, body);
    return resp.getData();
  }

  /**
   * Solves large routing problems
   * This endpoint solves large problems, i.e. traveling salesman or vehicle routing problems, and returns the solution.
   * @param key your API key
   * @param body Request object that contains the problem to be solved
   * @return ApiResponse<JobId>
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public ApiResponse<JobId> postVrpWithHttpInfo(String key, Request body) throws ApiException {
    Call call = postVrpCall(key, body, null, null);
    Type returnType = new TypeToken<JobId>(){}.getType();
    return apiClient.execute(call, returnType);
  }

  /**
   * Solves large routing problems (asynchronously)
   * This endpoint solves large problems, i.e. traveling salesman or vehicle routing problems, and returns the solution.
   * @param key your API key
   * @param body Request object that contains the problem to be solved
   * @param callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   */
  public Call postVrpAsync(String key, Request body, final ApiCallback<JobId> callback) throws ApiException {

    ProgressResponseBody.ProgressListener progressListener = null;
    ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

    if (callback != null) {
      progressListener = new ProgressResponseBody.ProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
          callback.onDownloadProgress(bytesRead, contentLength, done);
        } 
      };

      progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
        @Override
        public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
          callback.onUploadProgress(bytesWritten, contentLength, done);
        }
      };
    }

    Call call = postVrpCall(key, body, progressListener, progressRequestListener);
    Type returnType = new TypeToken<JobId>(){}.getType();
    apiClient.executeAsync(call, returnType, callback);
    return call;
  }
  
}
