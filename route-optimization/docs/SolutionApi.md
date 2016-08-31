# SolutionApi

All URIs are relative to *https://graphhopper.com/api/1/vrp*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSolution**](SolutionApi.md#getSolution) | **GET** /solution/{jobId} | Return the solution associated to the jobId


<a name="getSolution"></a>
# **getSolution**
> Response getSolution(key, jobId)

Return the solution associated to the jobId

This endpoint returns the solution of a large problems. You can fetch it with the job_id, you have been sent. 

### Example
```java
// Import classes:
//import com.graphhopper.routeopt.client.ApiClient;
//import com.graphhopper.routeopt.client.ApiException;
//import com.graphhopper.routeopt.client.Configuration;
//import com.graphhopper.routeopt.client.auth.*;
//import com.graphhopper.routeopt.client.api.SolutionApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

SolutionApi apiInstance = new SolutionApi();
String key = "key_example"; // String | your API key
String jobId = "jobId_example"; // String | Request solution with jobId
try {
    Response result = apiInstance.getSolution(key, jobId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SolutionApi#getSolution");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| your API key |
 **jobId** | **String**| Request solution with jobId |

### Return type

[**Response**](Response.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

