# directions-api-java-client-geocoding

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
    <artifactId>directions-api-java-client-geocoding</artifactId>
    <version>0.8-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.graphhopper:directions-api-java-client-geocoding:0.8-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/directions-api-java-client-geocoding-0.8-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.GeocodingApi;

import java.io.File;
import java.util.*;

public class GeocodingApiExample {

    public static void main(String[] args) {
        
        GeocodingApi apiInstance = new GeocodingApi();
        String point = "point_example"; // String | If you do forward geocoding, then this would be a textual description of the adress you are looking for. If you do reverse geocoding this would be in lat,lon.
        String key = "key_example"; // String | Get your key at graphhopper.com
        try {
            GeocodingResult result = apiInstance.geocodeGet(point, key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://graphhopper.com/api/1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*GeocodingApi* | [**geocodeGet**](docs/GeocodingApi.md#geocodeGet) | **GET** /geocode | Execute a Geocoding request


## Documentation for Models

 - [Error](docs/Error.md)
 - [GeocodingResult](docs/GeocodingResult.md)
 - [Location](docs/Location.md)
 - [Point](docs/Point.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



