# Java client for the Directions API

You can refer to this client in your pom.xml via
```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client</artifactId>
  <version>0.4.0-RC1</version>
</dependency>   
```

## Build Latest Development Version

```bash
git clone https://github.com/graphhopper/directions-api-js-client/
mvn -DskipTests=true clean install assembly:single
java -jar target/*with-dependencies.jar key=[YOUR_API_KEY]
```