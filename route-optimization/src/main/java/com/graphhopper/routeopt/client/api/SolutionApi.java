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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private Call getSolutionCall(String key, String jobId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'key' is set
    if (key == null) {
       throw new ApiException("Missing the required parameter 'key' when calling getSolution(Async)");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
       throw new ApiException("Missing the required parameter 'jobId' when calling getSolution(Async)");
    }
    

    // create path and map variables
    String localVarPath = "/solution/{jobId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "jobId" + "\\}", apiClient.escapeString(jobId.toString()));

    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    if (key != null)
      localVarQueryParams.addAll(apiClient.parameterToPairs("", "key", key));

    Map<String, String> localVarHeaderParams = new HashMap<String, String>();

    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
    if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
    localVarHeaderParams.put("Content-Type", localVarContentType);

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

    String[] localVarAuthNames = new String[] { "api_key" };
    return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
  }

  /**
   * Return the solution associated to the jobId
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.\n
   * @param key your API key (required)
   * @param jobId Request solution with jobId (required)
   * @return com.graphhopper.routeopt.client.model.Response
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public com.graphhopper.routeopt.client.model.Response getSolution(String key, String jobId) throws ApiException {
    ApiResponse<com.graphhopper.routeopt.client.model.Response> resp = getSolutionWithHttpInfo(key, jobId);
    return resp.getData();
  }

  /**
   * Return the solution associated to the jobId
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.\n
   * @param key your API key (required)
   * @param jobId Request solution with jobId (required)
   * @return ApiResponse<com.graphhopper.routeopt.client.model.Response>
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public ApiResponse<com.graphhopper.routeopt.client.model.Response> getSolutionWithHttpInfo(String key, String jobId) throws ApiException {
    Call call = getSolutionCall(key, jobId, null, null);
    Type localVarReturnType = new TypeToken<com.graphhopper.routeopt.client.model.Response>(){}.getType();
    return apiClient.execute(call, localVarReturnType);
  }

  /**
   * Return the solution associated to the jobId (asynchronously)
   * This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent.\n
   * @param key your API key (required)
   * @param jobId Request solution with jobId (required)
   * @param callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   */
  public Call getSolutionAsync(String key, String jobId, final ApiCallback<Response> callback) throws ApiException {

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

    Call call = getSolutionCall(key, jobId, progressListener, progressRequestListener);
    Type localVarReturnType = new TypeToken<Response>(){}.getType();
    apiClient.executeAsync(call, localVarReturnType, callback);
    return call;
  }
  
}
