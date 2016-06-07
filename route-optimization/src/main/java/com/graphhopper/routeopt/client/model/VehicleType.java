package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleType vehicleType = (VehicleType) o;
    return Objects.equals(this.typeId, vehicleType.typeId) &&
        Objects.equals(this.profile, vehicleType.profile) &&
        Objects.equals(this.capacity, vehicleType.capacity) &&
        Objects.equals(this.speedFactor, vehicleType.speedFactor) &&
        Objects.equals(this.serviceTimeFactor, vehicleType.serviceTimeFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeId, profile, capacity, speedFactor, serviceTimeFactor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VehicleType {\n");
    
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
    sb.append("    capacity: ").append(toIndentedString(capacity)).append("\n");
    sb.append("    speedFactor: ").append(toIndentedString(speedFactor)).append("\n");
    sb.append("    serviceTimeFactor: ").append(toIndentedString(serviceTimeFactor)).append("\n");
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
