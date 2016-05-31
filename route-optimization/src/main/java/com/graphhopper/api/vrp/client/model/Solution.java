package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import java.util.*;
import com.graphhopper.api.vrp.client.model.Route;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Solution   {
  
  private Integer costs = null;
  private Integer distance = null;
  private Long time = null;
  private Integer noUnassigned = null;
  private List<Route> routes = new ArrayList<Route>();
  private Object unassigned = null;

  
  /**
   * overall costs of solution
   **/
  @ApiModelProperty(value = "overall costs of solution")
  @JsonProperty("costs")
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
  @JsonProperty("distance")
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
  @JsonProperty("time")
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
  @JsonProperty("no_unassigned")
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
  @JsonProperty("routes")
  public List<Route> getRoutes() {
    return routes;
  }
  public void setRoutes(List<Route> routes) {
    this.routes = routes;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("unassigned")
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
