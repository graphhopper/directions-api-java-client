# Java client for the Directions API

We'll release this client as a maven dependency shortly. For now you have to build it manually. First steps to play with the code is to get this repository:

`git clone https://github.com/graphhopper/directions-api-js-client/`

Build it:
`mvn -DskipTests=true clean install`

Now you can refer to this client in your pom.xml via
```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client</artifactId>
  <version>0.4-SNAPSHOT</version>
</dependency>   
```

## Example

To run a simple example do:

`mvn -DskipTests=true clean install assembly:single`

`java -jar target/*with-dependencies.jar key=[YOUR_API_KEY]`
