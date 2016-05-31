package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import java.util.*;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class VehicleType   {
  
  private String typeId = null;

public enum ProfileEnum {
  CAR("car"),
  BIKE("bike"),
  FOOT("foot"),
  MTB("mtb"),
  MOTORCYCLE("motorcycle"),
  RACINGBIKE("racingbike");

  private String value;

  ProfileEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  private ProfileEnum profile = null;
  private List<Integer> capacity = new ArrayList<Integer>();
  private Double speedFactor = null;

  
  /**
   * Unique identifier for the vehicle type
   **/
  @ApiModelProperty(value = "Unique identifier for the vehicle type")
  @JsonProperty("type_id")
  public String getTypeId() {
    return typeId;
  }
  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  
  /**
   * Profile of vehicle type
   **/
  @ApiModelProperty(value = "Profile of vehicle type")
  @JsonProperty("profile")
  public ProfileEnum getProfile() {
    return profile;
  }
  public void setProfile(ProfileEnum profile) {
    this.profile = profile;
  }

  
  /**
   * array of capacity dimensions
   **/
  @ApiModelProperty(value = "array of capacity dimensions")
  @JsonProperty("capacity")
  public List<Integer> getCapacity() {
    return capacity;
  }
  public void setCapacity(List<Integer> capacity) {
    this.capacity = capacity;
  }

  
  /**
   * speed_factor of vehicle type
   **/
  @ApiModelProperty(value = "speed_factor of vehicle type")
  @JsonProperty("speed_factor")
  public Double getSpeedFactor() {
    return speedFactor;
  }
  public void setSpeedFactor(Double speedFactor) {
    this.speedFactor = speedFactor;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VehicleType {\n");
    
    sb.append("    typeId: ").append(StringUtil.toIndentedString(typeId)).append("\n");
    sb.append("    profile: ").append(StringUtil.toIndentedString(profile)).append("\n");
    sb.append("    capacity: ").append(StringUtil.toIndentedString(capacity)).append("\n");
    sb.append("    speedFactor: ").append(StringUtil.toIndentedString(speedFactor)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
