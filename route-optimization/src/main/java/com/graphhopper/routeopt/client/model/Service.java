package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import com.graphhopper.routeopt.client.model.TimeWindow;
import com.graphhopper.routeopt.client.model.Address;
import java.util.*;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Service   {
  
  @SerializedName("id")
  private String id = null;
  

public enum TypeEnum {
  @SerializedName("service")
  SERVICE("service"),

  @SerializedName("pickup")
  PICKUP("pickup"),

  @SerializedName("delivery")
  DELIVERY("delivery");

  private String value;

  TypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("type")
  private TypeEnum type = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("address")
  private Address address = null;
  
  @SerializedName("duration")
  private Long duration = null;
  
  @SerializedName("time_windows")
  private List<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
  
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
   * type of service
   **/
  @ApiModelProperty(value = "type of service")
  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }

  
  /**
   * name of service
   **/
  @ApiModelProperty(value = "name of service")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }

  
  /**
   * duration of service, i.e. time in ms the corresponding activity takes
   **/
  @ApiModelProperty(value = "duration of service, i.e. time in ms the corresponding activity takes")
  public Long getDuration() {
    return duration;
  }
  public void setDuration(Long duration) {
    this.duration = duration;
  }

  
  /**
   * array of time windows. currently, only a single time window is allowed
   **/
  @ApiModelProperty(value = "array of time windows. currently, only a single time window is allowed")
  public List<TimeWindow> getTimeWindows() {
    return timeWindows;
  }
  public void setTimeWindows(List<TimeWindow> timeWindows) {
    this.timeWindows = timeWindows;
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
    sb.append("class Service {\n");
    
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    name: ").append(StringUtil.toIndentedString(name)).append("\n");
    sb.append("    address: ").append(StringUtil.toIndentedString(address)).append("\n");
    sb.append("    duration: ").append(StringUtil.toIndentedString(duration)).append("\n");
    sb.append("    timeWindows: ").append(StringUtil.toIndentedString(timeWindows)).append("\n");
    sb.append("    size: ").append(StringUtil.toIndentedString(size)).append("\n");
    sb.append("    requiredSkills: ").append(StringUtil.toIndentedString(requiredSkills)).append("\n");
    sb.append("    allowedVehicles: ").append(StringUtil.toIndentedString(allowedVehicles)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
