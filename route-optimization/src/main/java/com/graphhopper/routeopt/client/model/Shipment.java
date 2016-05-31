package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import java.util.*;
import com.graphhopper.routeopt.client.model.Stop;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Shipment   {
  
  @SerializedName("id")
  private String id = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("pickup")
  private Stop pickup = null;
  
  @SerializedName("delivery")
  private Stop delivery = null;
  
  @SerializedName("size")
  private List<Integer> size = new ArrayList<Integer>();
  
  @SerializedName("required_skills")
  private List<String> requiredSkills = new ArrayList<String>();
  
  @SerializedName("allowed_vehicles")
  private List<String> allowedVehicles = new ArrayList<String>();
  

  
  /**
   * Unique identifier of service
   **/
  @ApiModelProperty(value = "Unique identifier of service")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * name of shipment
   **/
  @ApiModelProperty(value = "name of shipment")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Stop getPickup() {
    return pickup;
  }
  public void setPickup(Stop pickup) {
    this.pickup = pickup;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Stop getDelivery() {
    return delivery;
  }
  public void setDelivery(Stop delivery) {
    this.delivery = delivery;
  }

  
  /**
   * array of capacity dimensions
   **/
  @ApiModelProperty(value = "array of capacity dimensions")
  public List<Integer> getSize() {
    return size;
  }
  public void setSize(List<Integer> size) {
    this.size = size;
  }

  
  /**
   * array of required skills
   **/
  @ApiModelProperty(value = "array of required skills")
  public List<String> getRequiredSkills() {
    return requiredSkills;
  }
  public void setRequiredSkills(List<String> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  
  /**
   * array of allowed vehicle ids
   **/
  @ApiModelProperty(value = "array of allowed vehicle ids")
  public List<String> getAllowedVehicles() {
    return allowedVehicles;
  }
  public void setAllowedVehicles(List<String> allowedVehicles) {
    this.allowedVehicles = allowedVehicles;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Shipment {\n");
    
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    name: ").append(StringUtil.toIndentedString(name)).append("\n");
    sb.append("    pickup: ").append(StringUtil.toIndentedString(pickup)).append("\n");
    sb.append("    delivery: ").append(StringUtil.toIndentedString(delivery)).append("\n");
    sb.append("    size: ").append(StringUtil.toIndentedString(size)).append("\n");
    sb.append("    requiredSkills: ").append(StringUtil.toIndentedString(requiredSkills)).append("\n");
    sb.append("    allowedVehicles: ").append(StringUtil.toIndentedString(allowedVehicles)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
