package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import com.graphhopper.routeopt.client.model.Route;
import com.graphhopper.routeopt.client.model.SolutionUnassigned;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





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
  private SolutionUnassigned unassigned = null;
  

  
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
  public SolutionUnassigned getUnassigned() {
    return unassigned;
  }
  public void setUnassigned(SolutionUnassigned unassigned) {
    this.unassigned = unassigned;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Solution solution = (Solution) o;
    return Objects.equals(this.costs, solution.costs) &&
        Objects.equals(this.distance, solution.distance) &&
        Objects.equals(this.time, solution.time) &&
        Objects.equals(this.noUnassigned, solution.noUnassigned) &&
        Objects.equals(this.routes, solution.routes) &&
        Objects.equals(this.unassigned, solution.unassigned);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costs, distance, time, noUnassigned, routes, unassigned);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Solution {\n");
    
    sb.append("    costs: ").append(toIndentedString(costs)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    noUnassigned: ").append(toIndentedString(noUnassigned)).append("\n");
    sb.append("    routes: ").append(toIndentedString(routes)).append("\n");
    sb.append("    unassigned: ").append(toIndentedString(unassigned)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
