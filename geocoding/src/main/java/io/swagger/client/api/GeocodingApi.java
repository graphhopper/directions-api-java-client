package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

import io.swagger.client.model.GeocodingResult;
import io.swagger.client.model.Error;

import java.lang.reflect.Type;
import java.util.*;

public class GeocodingApi {
  private ApiClient apiClient;

  public GeocodingApi() {
    this(Configuration.getDefaultApiClient());
  }

  public GeocodingApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /* Build call for geocodeGet */
  private Call geocodeGetCall(String key, String q, String locale, Integer limit, Boolean reverse, String point, String provider, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'key' is set
    if (key == null) {
       throw new ApiException("Missing the required parameter 'key' when calling geocodeGet(Async)");
    }
    

    // create path and map variables
    String path = "/geocode".replaceAll("\\{format\\}","json");

    List<Pair> queryParams = new ArrayList<Pair>();
    if (q != null)
      queryParams.addAll(apiClient.parameterToPairs("", "q", q));
    if (locale != null)
      queryParams.addAll(apiClient.parameterToPairs("", "locale", locale));
    if (limit != null)
      queryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
    if (reverse != null)
      queryParams.addAll(apiClient.parameterToPairs("", "reverse", reverse));
    if (point != null)
      queryParams.addAll(apiClient.parameterToPairs("", "point", point));
    if (provider != null)
      queryParams.addAll(apiClient.parameterToPairs("", "provider", provider));
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

    String[] authNames = new String[] {  };
    return apiClient.buildCall(path, "GET", queryParams, postBody, headerParams, formParams, authNames, progressRequestListener);
  }

  /**
   * Execute a Geocoding request
   * This endpoint provides forward and reverse geocoding. For more details, review the official documentation at: https://graphhopper.com/api/1/docs/geocoding/
   * @param key Get your key at graphhopper.com
   * @param q If you do forward geocoding, then this would be a textual description of the adress you are looking for. If you do reverse geocoding this would be in lat,lon.
   * @param locale Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn&#39;t found the default (en) is used.
   * @param limit Specify the maximum number of returned results
   * @param reverse Set to true to do a reverse Geocoding request
   * @param point The location bias in the format &#39;latitude,longitude&#39; e.g. point=45.93272,11.58803
   * @param provider Can be either, default, nominatim, opencagedata
   * @return GeocodingResult
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public GeocodingResult geocodeGet(String key, String q, String locale, Integer limit, Boolean reverse, String point, String provider) throws ApiException {
    ApiResponse<GeocodingResult> resp = geocodeGetWithHttpInfo(key, q, locale, limit, reverse, point, provider);
    return resp.getData();
  }

  /**
   * Execute a Geocoding request
   * This endpoint provides forward and reverse geocoding. For more details, review the official documentation at: https://graphhopper.com/api/1/docs/geocoding/
   * @param key Get your key at graphhopper.com
   * @param q If you do forward geocoding, then this would be a textual description of the adress you are looking for. If you do reverse geocoding this would be in lat,lon.
   * @param locale Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn&#39;t found the default (en) is used.
   * @param limit Specify the maximum number of returned results
   * @param reverse Set to true to do a reverse Geocoding request
   * @param point The location bias in the format &#39;latitude,longitude&#39; e.g. point=45.93272,11.58803
   * @param provider Can be either, default, nominatim, opencagedata
   * @return ApiResponse<GeocodingResult>
   * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
   */
  public ApiResponse<GeocodingResult> geocodeGetWithHttpInfo(String key, String q, String locale, Integer limit, Boolean reverse, String point, String provider) throws ApiException {
    Call call = geocodeGetCall(key, q, locale, limit, reverse, point, provider, null, null);
    Type returnType = new TypeToken<GeocodingResult>(){}.getType();
    return apiClient.execute(call, returnType);
  }

  /**
   * Execute a Geocoding request (asynchronously)
   * This endpoint provides forward and reverse geocoding. For more details, review the official documentation at: https://graphhopper.com/api/1/docs/geocoding/
   * @param key Get your key at graphhopper.com
   * @param q If you do forward geocoding, then this would be a textual description of the adress you are looking for. If you do reverse geocoding this would be in lat,lon.
   * @param locale Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn&#39;t found the default (en) is used.
   * @param limit Specify the maximum number of returned results
   * @param reverse Set to true to do a reverse Geocoding request
   * @param point The location bias in the format &#39;latitude,longitude&#39; e.g. point=45.93272,11.58803
   * @param provider Can be either, default, nominatim, opencagedata
   * @param callback The callback to be executed when the API call finishes
   * @return The request call
   * @throws ApiException If fail to process the API call, e.g. serializing the request body object
   */
  public Call geocodeGetAsync(String key, String q, String locale, Integer limit, Boolean reverse, String point, String provider, final ApiCallback<GeocodingResult> callback) throws ApiException {

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

    Call call = geocodeGetCall(key, q, locale, limit, reverse, point, provider, progressListener, progressRequestListener);
    Type returnType = new TypeToken<GeocodingResult>(){}.getType();
    apiClient.executeAsync(call, returnType, callback);
    return call;
  }
  
}
