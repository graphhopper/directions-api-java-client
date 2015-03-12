# Java and Android client for the GraphHopper Directions API

You can refer to this client in your pom.xml via
```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client</artifactId>
  <version>0.4.0</version>
</dependency>
```

The latest development version is 0.5-SNAPSHOT

## Usage

See [GraphHopperWebTest](./src/test/java/com/graphhopper/api/GraphHopperWebTest.java#L30)

## Build Latest Development Version

```bash
git clone https://github.com/graphhopper/directions-api-js-client/
mvn -DskipTests=true clean install assembly:single

# now execute the test and set your key
mvn -Dgraphhopper.key=[YOUR_KEY] clean test
```