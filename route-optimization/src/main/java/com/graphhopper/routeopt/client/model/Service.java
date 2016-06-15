package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import com.graphhopper.routeopt.client.model.Address;
import com.graphhopper.routeopt.client.model.TimeWindow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





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
  
  @SerializedName("priority")
  private Integer priority = null;
  
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
   * priority of service, i.e. 1 = high, 2 = normal, 3 = low. default is 2.
   **/
  @ApiModelProperty(value = "priority of service, i.e. 1 = high, 2 = normal, 3 = low. default is 2.")
  public Integer getPriority() {
    return priority;
  }
  public void setPriority(Integer priority) {
    this.priority = priority;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Service service = (Service) o;
    return Objects.equals(this.id, service.id) &&
        Objects.equals(this.type, service.type) &&
        Objects.equals(this.priority, service.priority) &&
        Objects.equals(this.name, service.name) &&
        Objects.equals(this.address, service.address) &&
        Objects.equals(this.duration, service.duration) &&
        Objects.equals(this.timeWindows, service.timeWindows) &&
        Objects.equals(this.size, service.size) &&
        Objects.equals(this.requiredSkills, service.requiredSkills) &&
        Objects.equals(this.allowedVehicles, service.allowedVehicles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, priority, name, address, duration, timeWindows, size, requiredSkills, allowedVehicles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Service {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    timeWindows: ").append(toIndentedString(timeWindows)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    requiredSkills: ").append(toIndentedString(requiredSkills)).append("\n");
    sb.append("    allowedVehicles: ").append(toIndentedString(allowedVehicles)).append("\n");
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
