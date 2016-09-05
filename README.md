# Java and Android client for the GraphHopper Directions API

You can refer to this client in your pom.xml via
```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client</artifactId>
  <version>0.7.0.1</version>
</dependency>
```

The latest development version is 0.8-SNAPSHOT

## License

Apache License 2.0

## Usage

```java
GraphHopperWeb gh = new GraphHopperWeb();
// insert your key here
gh.setKey("YOUR_KEY");
// change timeout, default is 5 seconds
gh.setDownloader(new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).build());

// specify at least two coordinates
GHRequest req = new GHRequest().
   addPoint(new GHPoint(49.6724, 11.3494)).
   addPoint(new GHPoint(49.6550, 11.4180));
// Set vehicle like car, bike, foot, ...
req.setVehicle("bike");
// Optionally enable/disable elevation in output PointList, currently bike and foot support elevation, default is false
req.getHints().put("elevation", false);
// Optionally enable/disable turn instruction information, defaults is true
req.getHints().put("instructions", true);
// Optionally enable/disable path geometry information, default is true
req.getHints().put("calc_points", true);
// note: turn off instructions and calcPoints if you just need the distance or time 
// information to make calculation and transmission faster
//
// Optionally set specific locale for instruction information, supports already over 25 languages,
// defaults to English
req.setLocale(Locale.GERMAN);

GHResponse fullRes = gh.route(req);
        
if(res.hasErrors()) {
   // handle or throw exceptions res.getErrors()
   return;
}

PathWrapper res = fullRes.getBest();
// get path geometry information (latitude, longitude and optionally elevation)
PointList pl = res.getPoints();
// distance of the full path, in meter
double distance = res.getDistance();
// time of the full path, in milliseconds
long millis = res.getTime();
// get information per turn instruction
InstructionList il = res.getInstructions();
```

### Matrix API

```java
GraphHopperMatrixWeb matrixClient = new GraphHopperMatrixWeb();
matrixClient.setKey("[YOUR_KEY]");

GHMRequest ghmRequest = new GHMRequest();
ghmRequest.addOutArray("distances");
ghmRequest.addOutArray("times");
ghmRequest.setVehicle("car");

// init points for a symmetric matrix
List<GHPoint> allPoints = ...;
ghmRequest.addAllPoints(allPoints);

// or init e.g. a one-to-many matrix:
ghmRequest.addFromPoint(from);
for(GHPoint to : toList) {
   ghmRequest.addToPoint(to);
}

MatrixResponse response = matrixClient.route(ghmRequest);
GHMResponse singleRsp = response.get(fromIndex, toIndex);
singleRsp.getDistance();
...
```

## Build Latest Development Version

```bash
git clone https://github.com/graphhopper/directions-api-java-client/
cd client
mvn -DskipTests=true clean install

# now execute the test and set your key
mvn -Dgraphhopper.key=[YOUR_KEY] clean test verify
```

## Android 

It is important to use this client not on the main thread of Android as it could block the app. 
See [issue 7](https://github.com/graphhopper/directions-api-java-client/issues/7) for more information.

### Android Alternative

The OSM [OSMBonusPack](https://github.com/MKergall/osmbonuspack/wiki/Tutorial_1) also has
an Android client written from the community. It also supports 
online map tiles. You can see this code in action in 
[Geopaparazzi](http://geopaparazzi.github.io/geopaparazzi/), 
[OSMNavigator](https://github.com/MKergall/osmbonuspack/wiki/OSMNavigator) and [more](https://github.com/geopaparazzi/geopaparazzi/wiki/Projects-Using-It)

# Route Optimization API

If you setOptimize("true") then the locations will be optimized according to the best overall route.
For more advanced features like multiple vehicles and capacity restrictions etc. you need to call the Route Optimization API.
The client for the Route Optimization API is located in the folder "route-optimization".

## Usage

See the [examples](./route-optimization/src/main/java/com/graphhopper/routeopt/examples) on how to use this client.

## Maven

The route optimization client is in the maven central repository, thus you can just reference it in your pom like:

```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client-route-opt</artifactId>
  <version>[CURRENT-VERSION]</version>
</dependency>
```

## Customization

If you want to build it from source please follow the guide for java [here](https://github.com/graphhopper/directions-api-clients-route-optimization).
