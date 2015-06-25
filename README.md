# Java and Android client for the GraphHopper Directions API

You can refer to this client in your pom.xml via
```xml
<dependency>
  <groupId>com.graphhopper</groupId>
  <artifactId>directions-api-java-client</artifactId>
  <version>0.4.1.4</version>
</dependency>
```

The latest development version is 0.5-SNAPSHOT

## Usage

```java
GraphHopperWeb gh = new GraphHopperWeb();
// insert your key here
gh.setKey("YOUR_KEY");
// change timeout, default is 5 seconds
gh.getDownloader().setConnectTimeout(10, TimeUnit.SECONDS);

// specify at least two coordinates
GHRequest req = new GHRequest().
   addPoint(new GHPoint(49.6724, 11.3494)).
   addPoint(new GHPoint(49.6550, 11.4180));
// Set vehicle like car, bike and foot
req.setVehicle("bike");
// Optionally enable/disable elevation in output PointList, currently bike and foot support elevation, default is false
req.getHints().put("elevation", false);
// Optionally enable/disable turn instruction information, defaults is true
req.getHints().put("instructions", true);
// Optionally enable/disable path geometry information, default is true
req.getHints().put("calcPoints", true);
// note: turn off instructions and calcPoints if you just need the distance or time 
// information to make calculation and transmission faster
//
// Optionally set specific locale for instruction information, supports already over 25 languages,
// defaults to English
req.setLocale(Locale.GERMAN);

GHResponse res = gh.route(req);
        
if(res.hasErrors()) {
   // handle or throw exceptions res.getErrors()
   return;
}

// get path geometry information (latitude, longitude and optionally elevation)
PointList pl = res.getPoints();
// distance of the full path, in meter
double distance = res.getDistance();
// time of the full path, in milliseconds
long millis = res.getTime();
// get information per turn instruction
InstructionList il = res.getInstructions();
```

## Build Latest Development Version

```bash
git clone https://github.com/graphhopper/directions-api-java-client/
mvn -DskipTests=true clean install

# now execute the test and set your key
mvn -Dgraphhopper.key=[YOUR_KEY] clean test verify
```

## Android Alternative

The OSM [OSMBonusPack](https://github.com/MKergall/osmbonuspack/wiki/Tutorial_1) also has
an Android client written from the community. It also supports 
online map tiles. You can see this code in action in 
[Geopaparazzi](http://geopaparazzi.github.io/geopaparazzi/), 
[OSMNavigator](https://github.com/MKergall/osmbonuspack/wiki/OSMNavigator) and [more](https://github.com/geopaparazzi/geopaparazzi/wiki/Projects-Using-It)

## Tour Optimization API

The client for the Tour Optimization API is currently hosted in a [separate repository](https://github.com/karussell/directions-api-vrp-java-client/).
