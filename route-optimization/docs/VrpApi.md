# VrpApi

All URIs are relative to *https://graphhopper.com/api/1/vrp*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postVrp**](VrpApi.md#postVrp) | **POST** /optimize | Solves vehicle routing problems


<a name="postVrp"></a>
# **postVrp**
> JobId postVrp(key, body)

Solves vehicle routing problems

This endpoint for solving vehicle routing problems, i.e. traveling salesman or vehicle routing problems, and returns the solution. 

### Example
```java
// Import classes:
//import com.graphhopper.routeopt.client.ApiClient;
//import com.graphhopper.routeopt.client.ApiException;
//import com.graphhopper.routeopt.client.Configuration;
//import com.graphhopper.routeopt.client.auth.*;
//import com.graphhopper.routeopt.client.api.VrpApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: api_key
ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
api_key.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//api_key.setApiKeyPrefix("Token");

VrpApi apiInstance = new VrpApi();
String key = "key_example"; // String | your API key
Request body = new Request(); // Request | Request object that contains the problem to be solved
try {
    JobId result = apiInstance.postVrp(key, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling VrpApi#postVrp");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| your API key |
 **body** | [**Request**](Request.md)| Request object that contains the problem to be solved |

### Return type

[**JobId**](JobId.md)

### Authorization

[api_key](../README.md#api_key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

