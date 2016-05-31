package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import java.util.*;
import com.graphhopper.routeopt.client.model.Route;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Solution   {
  
  @SerializedName("costs")
  private Integer costs = null;
  
  @SerializedName("distance")
  private Integer distance = null;
  
  @SerializedName("time")
  private Long time = null;
  
  @SerializedName("no_unassigned")
  private Integer noUnassigned = null;
  
  @SerializedName("routes")
  private List<Route> routes = new ArrayList<Route>();
  
  @SerializedName("unassigned")
  private Object unassigned = null;
  

  
  /**
   * overall costs of solution
   **/
  @ApiModelProperty(value = "overall costs of solution")
  public Integer getCosts() {
    return costs;
  }
  public void setCosts(Integer costs) {
    this.costs = costs;
  }

  
  /**
   * overall travel distance in meters
   **/
  @ApiModelProperty(value = "overall travel distance in meters")
  public Integer getDistance() {
    return distance;
  }
  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  
  /**
   * overall travel time in ms
   **/
  @ApiModelProperty(value = "overall travel time in ms")
  public Long getTime() {
    return time;
  }
  public void setTime(Long time) {
    this.time = time;
  }

  
  /**
   * number of jobs that could not be assigned to final solution
   **/
  @ApiModelProperty(value = "number of jobs that could not be assigned to final solution")
  public Integer getNoUnassigned() {
    return noUnassigned;
  }
  public void setNoUnassigned(Integer noUnassigned) {
    this.noUnassigned = noUnassigned;
  }

  
  /**
   * An array of routes
   **/
  @ApiModelProperty(value = "An array of routes")
  public List<Route> getRoutes() {
    return routes;
  }
  public void setRoutes(List<Route> routes) {
    this.routes = routes;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Object getUnassigned() {
    return unassigned;
  }
  public void setUnassigned(Object unassigned) {
    this.unassigned = unassigned;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Solution {\n");
    
    sb.append("    costs: ").append(StringUtil.toIndentedString(costs)).append("\n");
    sb.append("    distance: ").append(StringUtil.toIndentedString(distance)).append("\n");
    sb.append("    time: ").append(StringUtil.toIndentedString(time)).append("\n");
    sb.append("    noUnassigned: ").append(StringUtil.toIndentedString(noUnassigned)).append("\n");
    sb.append("    routes: ").append(StringUtil.toIndentedString(routes)).append("\n");
    sb.append("    unassigned: ").append(StringUtil.toIndentedString(unassigned)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
