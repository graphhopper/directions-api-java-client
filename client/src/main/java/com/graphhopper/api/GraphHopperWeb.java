/*
 *  Licensed to GraphHopper and Peter Karich under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for 
 *  additional information regarding copyright ownership.
 * 
 *  GraphHopper licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except in 
 *  compliance with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.api;

import com.graphhopper.PathWrapper;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopperAPI;
import com.graphhopper.util.*;
import com.graphhopper.util.exceptions.ConnectionNotFoundException;
import com.graphhopper.util.exceptions.DetailedIllegalArgumentException;
import com.graphhopper.util.exceptions.DetailedRuntimeException;
import com.graphhopper.util.exceptions.PointNotFoundException;
import com.graphhopper.util.exceptions.PointOutOfBoundsException;
import com.graphhopper.util.shapes.GHPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main wrapper of the GraphHopper Directions API for a simple and efficient
 * usage.
 *
 * @author Peter Karich
 */
public class GraphHopperWeb implements GraphHopperAPI {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private OkHttpClient downloader;
    private String serviceUrl;
    private String key = "";
    private boolean instructions = true;
    private boolean calcPoints = true;
    private boolean elevation = false;
    private String optimize = "false";
    private final Set<String> ignoreSet;

    public GraphHopperWeb() {
        this("https://graphhopper.com/api/1/route");
    }

    public GraphHopperWeb(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        downloader = new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).
                build();

        // some parameters are supported directly via Java API so ignore them when writing the getHints map
        ignoreSet = new HashSet<String>();
        ignoreSet.add("calc_points");
        ignoreSet.add("calcpoints");
        ignoreSet.add("instructions");
        ignoreSet.add("elevation");
        ignoreSet.add("key");
        ignoreSet.add("optimize");

        // some parameters are in the request:
        ignoreSet.add("algorithm");
        ignoreSet.add("locale");
        ignoreSet.add("point");
        ignoreSet.add("vehicle");

        // some are special and need to be avoided
        ignoreSet.add("points_encoded");
        ignoreSet.add("pointsencoded");
        ignoreSet.add("type");
    }

    public GraphHopperWeb setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
        return this;
    }

    public OkHttpClient getDownloader() {
        return downloader;
    }

    @Override
    public boolean load(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return true;
    }

    public GraphHopperWeb setKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalStateException("Key cannot be empty");
        }

        this.key = key;
        return this;
    }

    /**
     * Enable or disable calculating points for the way. The default is true.
     */
    public GraphHopperWeb setCalcPoints(boolean calcPoints) {
        this.calcPoints = calcPoints;
        return this;
    }

    /**
     * Enable or disable calculating and returning turn instructions. The
     * default is true.
     */
    public GraphHopperWeb setInstructions(boolean b) {
        instructions = b;
        return this;
    }

    /**
     * Enable or disable elevation. The default is false.
     */
    public GraphHopperWeb setElevation(boolean withElevation) {
        this.elevation = withElevation;
        return this;
    }

    /**
     * @param optimize "false" if the order of the locations should be left
     * unchanged, this is the default. Or if "true" then the order of the
     * location is optimized according to the overall best route and returned
     * this way i.e. the traveling salesman problem is solved under the hood.
     * Note that in this case the request takes longer and costs more credits.
     * For more details see:
     * https://github.com/graphhopper/directions-api/blob/master/FAQ.md#what-is-one-credit
     */
    public GraphHopperWeb setOptimize(String optimize) {
        this.optimize = optimize;
        return this;
    }

    @Override
    public GHResponse route(GHRequest ghRequest) {
        String str = "Creating request failed";
        try {
            Request okRequest = createRequest(ghRequest);
            str = downloader.newCall(okRequest).execute().body().string();

            JSONObject json = new JSONObject(str);
            GHResponse res = new GHResponse();
            res.addErrors(readErrors(json));
            if (res.hasErrors()) {
                return res;
            }

            JSONArray paths = json.getJSONArray("paths");

            boolean tmpInstructions = ghRequest.getHints().getBool("instructions", instructions);
            boolean tmpCalcPoints = ghRequest.getHints().getBool("calc_points", calcPoints);
            boolean tmpElevation = ghRequest.getHints().getBool("elevation", elevation);

            for (int index = 0; index < paths.length(); index++) {
                JSONObject path = paths.getJSONObject(index);
                PathWrapper altRsp = createAltResponse(path, tmpCalcPoints, tmpInstructions, tmpElevation);
                res.add(altRsp);
            }
            return res;
        } catch (Exception ex) {
            throw new RuntimeException("Problem while fetching path " + ghRequest.getPoints()
                    + ", error: " + ex.getMessage() + " response: " + str, ex);
        }
    }

    // TODO Should we add this method to the GraphHopperApi?
    public String export(GHRequest ghRequest) {
        String str = "Creating request failed";
        try {
            Request okRequest = createRequest(ghRequest);
            str = downloader.newCall(okRequest).execute().body().string();

            return str;
        } catch (Exception ex) {
            throw new RuntimeException("Problem while fetching export " + ghRequest.getPoints()
                    + ", error: " + ex.getMessage() + " response: " + str, ex);
        }
    }

    public Request createRequest(GHRequest request) {
        boolean tmpInstructions = request.getHints().getBool("instructions", instructions);
        boolean tmpCalcPoints = request.getHints().getBool("calc_points", calcPoints);
        String tmpOptimize = request.getHints().get("optimize", optimize);

        if (tmpInstructions && !tmpCalcPoints) {
            throw new IllegalStateException("Cannot calculate instructions without points (only points without instructions). "
                    + "Use calc_points=false and instructions=false to disable point and instruction calculation");
        }

        boolean tmpElevation = request.getHints().getBool("elevation", elevation);

        String places = "";
        for (GHPoint p : request.getPoints()) {
            places += "point=" + Helper.round6(p.lat) + "," + Helper.round6(p.lon) + "&";
        }

        String type = request.getHints().get("type", "json");

        String url = serviceUrl
                + "?"
                + places
                + "&type=" + type
                + "&instructions=" + tmpInstructions
                + "&points_encoded=true"
                + "&calc_points=" + tmpCalcPoints
                + "&algorithm=" + request.getAlgorithm()
                + "&locale=" + request.getLocale().toString()
                + "&elevation=" + tmpElevation
                + "&optimize=" + tmpOptimize;

        if (!request.getVehicle().isEmpty()) {
            url += "&vehicle=" + request.getVehicle();
        }

        if (!key.isEmpty()) {
            url += "&key=" + WebHelper.encodeURL(key);
        }

        for (Map.Entry<String, String> entry : request.getHints().toMap().entrySet()) {
            String urlKey = entry.getKey();
            String urlValue = entry.getValue();

            // use lower case conversion for check only!
            if (ignoreSet.contains(urlKey.toLowerCase())) {
                continue;
            }

            if (urlValue != null && !urlValue.isEmpty()) {
                url += "&" + WebHelper.encodeURL(urlKey) + "=" + WebHelper.encodeURL(urlValue);
            }
        }

        return new Request.Builder().url(url).build();
    }

    public static PathWrapper createAltResponse(JSONObject path,
            boolean tmpCalcPoints, boolean tmpInstructions, boolean tmpElevation) {

        PathWrapper pathWrapper = new PathWrapper();
        pathWrapper.addErrors(readErrors(path));
        if (pathWrapper.hasErrors()) {
            return pathWrapper;
        }

        if (path.has("snapped_waypoints")) {
            String snappedPointStr = path.getString("snapped_waypoints");
            PointList snappedPoints = WebHelper.decodePolyline(snappedPointStr, 5, tmpElevation);
            pathWrapper.setWaypoints(snappedPoints);
        }

        if (tmpCalcPoints) {
            String pointStr = path.getString("points");
            PointList pointList = WebHelper.decodePolyline(pointStr, 100, tmpElevation);
            pathWrapper.setPoints(pointList);

            if (tmpInstructions) {
                JSONArray instrArr = path.getJSONArray("instructions");

                InstructionList il = new InstructionList(null);
                int viaCount = 1;
                for (int instrIndex = 0; instrIndex < instrArr.length(); instrIndex++) {
                    JSONObject jsonObj = instrArr.getJSONObject(instrIndex);
                    double instDist = jsonObj.getDouble("distance");
                    String text = jsonObj.getString("text");
                    long instTime = jsonObj.getLong("time");
                    int sign = jsonObj.getInt("sign");
                    JSONArray iv = jsonObj.getJSONArray("interval");
                    int from = iv.getInt(0);
                    int to = iv.getInt(1);
                    PointList instPL = new PointList(to - from, tmpElevation);
                    for (int j = from; j <= to; j++) {
                        instPL.add(pointList, j);
                    }

                    InstructionAnnotation ia = InstructionAnnotation.EMPTY;
                    if (jsonObj.has("annotation_importance") && jsonObj.has("annotation_text")) {
                        ia = new InstructionAnnotation(jsonObj.getInt("annotation_importance"), jsonObj.getString("annotation_text"));
                    }

                    Instruction instr;
                    if (sign == Instruction.USE_ROUNDABOUT || sign == Instruction.LEAVE_ROUNDABOUT) {
                        RoundaboutInstruction ri = new RoundaboutInstruction(sign, text, ia, instPL);

                        if (jsonObj.has("exit_number")) {
                            ri.setExitNumber(jsonObj.getInt("exit_number"));
                        }

                        if (jsonObj.has("turn_angle")) {
                            // TODO provide setTurnAngle setter
                            double angle = jsonObj.getDouble("turn_angle");
                            ri.setDirOfRotation(angle);
                            ri.setRadian((angle < 0 ? -Math.PI : Math.PI) - angle);
                        }

                        instr = ri;
                    } else if (sign == Instruction.REACHED_VIA) {
                        ViaInstruction tmpInstr = new ViaInstruction(text, ia, instPL);
                        tmpInstr.setViaCount(viaCount);
                        viaCount++;
                        instr = tmpInstr;
                    } else if (sign == Instruction.FINISH) {
                        instr = new FinishInstruction(text, instPL, 0);
                    } else {
                        instr = new Instruction(sign, text, ia, instPL);
                    }

                    // The translation is done from the routing service so just use the provided string
                    // instead of creating a combination with sign and name etc
                    instr.setUseRawName();

                    instr.setDistance(instDist).setTime(instTime);
                    il.add(instr);
                }
                pathWrapper.setInstructions(il);
            }
        }

        double distance = path.getDouble("distance");
        long time = path.getLong("time");
        pathWrapper.setDistance(distance).setTime(time);
        return pathWrapper;
    }

    // Credits to: http://stackoverflow.com/a/24012023/194609
    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>(object.keySet().size());
        for (String key : object.keySet()) {
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (Object value : array) {
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static List<Throwable> readErrors(JSONObject json) {
        List<Throwable> errors = new ArrayList<>();
        JSONArray errorJson;

        if (json.has("message")) {
            if (json.has("hints")) {
                errorJson = json.getJSONArray("hints");
            } else {
                // should not happen
                errors.add(new RuntimeException(json.getString("message")));
                return errors;
            }
        } else {
            return errors;
        }

        for (int i = 0; i < errorJson.length(); i++) {
            JSONObject error = errorJson.getJSONObject(i);
            String exClass = "";
            if (error.has("details")) {
                exClass = error.getString("details");
            }

            String exMessage = error.getString("message");

            if (exClass.equals(UnsupportedOperationException.class.getName())) {
                errors.add(new UnsupportedOperationException(exMessage));
            } else if (exClass.equals(IllegalStateException.class.getName())) {
                errors.add(new IllegalStateException(exMessage));
            } else if (exClass.equals(RuntimeException.class.getName())) {
                errors.add(new DetailedRuntimeException(exMessage, toMap(error)));
            } else if (exClass.equals(IllegalArgumentException.class.getName())) {
                errors.add(new DetailedIllegalArgumentException(exMessage, toMap(error)));
            } else if (exClass.equals(ConnectionNotFoundException.class.getName())) {
                errors.add(new ConnectionNotFoundException(exMessage, toMap(error)));
            } else if (exClass.equals(PointNotFoundException.class.getName())) {
                int pointIndex = error.getInt("point_index");
                errors.add(new PointNotFoundException(exMessage, pointIndex));
            } else if (exClass.equals(PointOutOfBoundsException.class.getName())) {
                int pointIndex = error.getInt("point_index");
                errors.add(new PointOutOfBoundsException(exMessage, pointIndex));
            } else if (exClass.isEmpty()) {
                errors.add(new DetailedRuntimeException(exMessage, toMap(error)));
            } else {
                errors.add(new DetailedRuntimeException(exClass + " " + exMessage, toMap(error)));
            }
        }

        if (json.has("message") && errors.isEmpty()) {
            errors.add(new RuntimeException(json.getString("message")));
        }

        return errors;
    }
}
