package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import java.util.*;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class VehicleType   {
  
  @SerializedName("type_id")
  private String typeId = null;
  

public enum ProfileEnum {
  @SerializedName("car")
  CAR("car"),

  @SerializedName("bike")
  BIKE("bike"),

  @SerializedName("foot")
  FOOT("foot"),

  @SerializedName("mtb")
  MTB("mtb"),

  @SerializedName("motorcycle")
  MOTORCYCLE("motorcycle"),

  @SerializedName("racingbike")
  RACINGBIKE("racingbike"),

  @SerializedName("truck")
  TRUCK("truck"),

  @SerializedName("small_truck")
  SMALL_TRUCK("small_truck"),

  @SerializedName("bus")
  BUS("bus");

  private String value;

  ProfileEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("profile")
  private ProfileEnum profile = null;
  
  @SerializedName("capacity")
  private List<Integer> capacity = new ArrayList<Integer>();
  
  @SerializedName("speed_factor")
  private Double speedFactor = null;
  
  @SerializedName("service_time_factor")
  private Double serviceTimeFactor = null;
  

  
  /**
   * Unique identifier for the vehicle type
   **/
  @ApiModelProperty(value = "Unique identifier for the vehicle type")
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
  public Double getSpeedFactor() {
    return speedFactor;
  }
  public void setSpeedFactor(Double speedFactor) {
    this.speedFactor = speedFactor;
  }

  
  /**
   * service time factor of vehicle type
   **/
  @ApiModelProperty(value = "service time factor of vehicle type")
  public Double getServiceTimeFactor() {
    return serviceTimeFactor;
  }
  public void setServiceTimeFactor(Double serviceTimeFactor) {
    this.serviceTimeFactor = serviceTimeFactor;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class VehicleType {\n");
    
    sb.append("    typeId: ").append(StringUtil.toIndentedString(typeId)).append("\n");
    sb.append("    profile: ").append(StringUtil.toIndentedString(profile)).append("\n");
    sb.append("    capacity: ").append(StringUtil.toIndentedString(capacity)).append("\n");
    sb.append("    speedFactor: ").append(StringUtil.toIndentedString(speedFactor)).append("\n");
    sb.append("    serviceTimeFactor: ").append(StringUtil.toIndentedString(serviceTimeFactor)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
