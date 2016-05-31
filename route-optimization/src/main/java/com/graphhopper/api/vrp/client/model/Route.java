package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Activity;
import java.util.*;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Route   {
  
  private String vehicleId = null;
  private List<Activity> activities = new ArrayList<Activity>();

  
  /**
   * id of vehicle that operates route
   **/
  @ApiModelProperty(value = "id of vehicle that operates route")
  @JsonProperty("vehicle_id")
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
  @JsonProperty("activities")
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
