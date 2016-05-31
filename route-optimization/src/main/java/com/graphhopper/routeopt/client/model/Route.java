package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import com.graphhopper.routeopt.client.model.Activity;
import java.util.*;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Route   {
  
  @SerializedName("vehicle_id")
  private String vehicleId = null;
  
  @SerializedName("activities")
  private List<Activity> activities = new ArrayList<Activity>();
  

  
  /**
   * id of vehicle that operates route
   **/
  @ApiModelProperty(value = "id of vehicle that operates route")
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  
  /**
   * array of activities
   **/
  @ApiModelProperty(value = "array of activities")
  public List<Activity> getActivities() {
    return activities;
  }
  public void setActivities(List<Activity> activities) {
    this.activities = activities;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Route {\n");
    
    sb.append("    vehicleId: ").append(StringUtil.toIndentedString(vehicleId)).append("\n");
    sb.append("    activities: ").append(StringUtil.toIndentedString(activities)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
