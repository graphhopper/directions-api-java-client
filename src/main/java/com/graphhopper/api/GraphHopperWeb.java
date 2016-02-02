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
import com.graphhopper.util.shapes.GHPoint;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
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
    private OkHttpClient downloader = new OkHttpClient();
    private String serviceUrl;
    private String key = "";
    private boolean instructions = true;
    private boolean calcPoints = true;
    private boolean elevation = false;
    private String optimize = "false";

    public GraphHopperWeb() {
        this("https://graphhopper.com/api/1/route");
    }

    public GraphHopperWeb(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        downloader.setConnectTimeout(5, TimeUnit.SECONDS);
    }

    public void setDownloader(OkHttpClient downloader) {
        this.downloader = downloader;
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
        try {
            Request okRequest = createRequest(ghRequest);
            String str = downloader.newCall(okRequest).execute().body().string();

            JSONObject json = new JSONObject(str);
            GHResponse res = new GHResponse();
            res.addErrors(readErrors(json));
            if (res.hasErrors()) {
                return res;
            }

            JSONArray paths = json.getJSONArray("paths");

            boolean tmpInstructions = ghRequest.getHints().getBool("instructions", instructions);
            boolean tmpCalcPoints = ghRequest.getHints().getBool("calcPoints", calcPoints);
            boolean tmpElevation = ghRequest.getHints().getBool("elevation", elevation);

            for (int index = 0; index < paths.length(); index++) {
                JSONObject path = paths.getJSONObject(index);
                PathWrapper altRsp = createAltResponse(path, tmpCalcPoints, tmpInstructions, tmpElevation);
                res.add(altRsp);
            }
            return res;
        } catch (Exception ex) {
            throw new RuntimeException("Problem while fetching path " + ghRequest.getPoints() + ": " + ex.getMessage(), ex);
        }
    }

    public Request createRequest(GHRequest request) {
        boolean tmpInstructions = request.getHints().getBool("instructions", instructions);
        boolean tmpCalcPoints = request.getHints().getBool("calcPoints", calcPoints);
        String tmpOptimize = request.getHints().get("optimize", optimize);

        if (tmpInstructions && !tmpCalcPoints) {
            throw new IllegalStateException("Cannot calculate instructions without points (only points without instructions). "
                    + "Use calcPoints=false and instructions=false to disable point and instruction calculation");
        }

        boolean tmpElevation = request.getHints().getBool("elevation", elevation);

        String tmpKey = request.getHints().get("key", key);

        String places = "";
        for (GHPoint p : request.getPoints()) {
            places += "point=" + p.lat + "," + p.lon + "&";
        }

        String url = serviceUrl
                + "?"
                + places
                + "&type=json"
                + "&instructions=" + tmpInstructions
                + "&points_encoded=true"
                + "&calc_points=" + tmpCalcPoints
                + "&algo=" + request.getAlgorithm()
                + "&locale=" + request.getLocale().toString()
                + "&elevation=" + tmpElevation
                + "&optimize=" + tmpOptimize;

        if (!request.getVehicle().isEmpty()) {
            url += "&vehicle=" + request.getVehicle();
        }

        if (!tmpKey.isEmpty()) {
            url += "&key=" + tmpKey;
        }

        return new Request.Builder().url(url).build();
    }

    public static PathWrapper createAltResponse(JSONObject path,
            boolean tmpCalcPoints, boolean tmpInstructions, boolean tmpElevation) {
        PathWrapper altRsp = new PathWrapper();
        altRsp.addErrors(readErrors(path));
        if (altRsp.hasErrors()) {
            return altRsp;
        }

        double distance = path.getDouble("distance");
        long time = path.getLong("time");
        if (tmpCalcPoints) {
            String pointStr = path.getString("points");
            PointList pointList = WebHelper.decodePolyline(pointStr, 100, tmpElevation);
            altRsp.setPoints(pointList);

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
                        instr = new FinishInstruction(instPL, 0);
                    } else {
                        instr = new Instruction(sign, text, ia, instPL);
                    }

                    // The translation is done from the routing service so just use the provided string
                    // instead of creating a combination with sign and name etc
                    instr.setUseRawName();

                    instr.setDistance(instDist).setTime(instTime);
                    il.add(instr);
                }
                altRsp.setInstructions(il);
            }
        }
        altRsp.setDistance(distance).setTime(time);
        return altRsp;
    }

    public static List<Throwable> readErrors(JSONObject json) {
        List<Throwable> errors = new ArrayList<Throwable>();

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
                errors.add(new RuntimeException(exMessage));
            } else if (exClass.equals(IllegalArgumentException.class.getName())) {
                errors.add(new IllegalArgumentException(exMessage));
            } else if (exClass.isEmpty()) {
                errors.add(new RuntimeException(exMessage));
            } else {
                errors.add(new RuntimeException(exClass + " " + exMessage));
            }
        }

        if (json.has("message") && errors.isEmpty()) {
            errors.add(new RuntimeException(json.getString("message")));
        }

        return errors;
    }
}
