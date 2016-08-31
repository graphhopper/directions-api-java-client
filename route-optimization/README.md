# directions-api-java-client-route-opt

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.graphhopper</groupId>
    <artifactId>directions-api-java-client-route-opt</artifactId>
    <version>0.8-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.graphhopper:directions-api-java-client-route-opt:0.8-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/directions-api-java-client-route-opt-0.8-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.graphhopper.routeopt.client.*;
import com.graphhopper.routeopt.client.auth.*;
import com.graphhopper.routeopt.client.model.*;
import com.graphhopper.routeopt.client.api.SolutionApi;

import java.io.File;
import java.util.*;

public class SolutionApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://graphhopper.com/api/1/vrp*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*SolutionApi* | [**getSolution**](docs/SolutionApi.md#getSolution) | **GET** /solution/{jobId} | Return the solution associated to the jobId
*VrpApi* | [**postVrp**](docs/VrpApi.md#postVrp) | **POST** /optimize | Solves vehicle routing problems


## Documentation for Models

 - [Activity](docs/Activity.md)
 - [Address](docs/Address.md)
 - [Algorithm](docs/Algorithm.md)
 - [CostMatrix](docs/CostMatrix.md)
 - [JobId](docs/JobId.md)
 - [ModelBreak](docs/ModelBreak.md)
 - [Objective](docs/Objective.md)
 - [Relation](docs/Relation.md)
 - [Request](docs/Request.md)
 - [Response](docs/Response.md)
 - [Route](docs/Route.md)
 - [Service](docs/Service.md)
 - [Shipment](docs/Shipment.md)
 - [Solution](docs/Solution.md)
 - [SolutionUnassigned](docs/SolutionUnassigned.md)
 - [Stop](docs/Stop.md)
 - [TimeWindow](docs/TimeWindow.md)
 - [Vehicle](docs/Vehicle.md)
 - [VehicleType](docs/VehicleType.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### api_key

- **Type**: API key
- **API key parameter name**: key
- **Location**: URL query string


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



