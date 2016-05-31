package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Address;
import java.util.*;
import com.graphhopper.api.vrp.client.model.TimeWindow;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Service   {
  
  private String id = null;

public enum TypeEnum {
  SERVICE("service");

  private String value;

  TypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  private TypeEnum type = null;
  private String name = null;
  private Address address = null;
  private Long duration = null;
  private List<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
  private List<Integer> size = new ArrayList<Integer>();
  private List<String> requiredSkills = new ArrayList<String>();

  
  /**
   * Unique identifier of service
   **/
  @ApiModelProperty(value = "Unique identifier of service")
  @JsonProperty("id")
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
  @JsonProperty("type")
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
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("address")
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
  @JsonProperty("duration")
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
  @JsonProperty("time_windows")
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
  @JsonProperty("size")
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
  @JsonProperty("required_skills")
  public List<String> getRequiredSkills() {
    return requiredSkills;
  }
  public void setRequiredSkills(List<String> requiredSkills) {
    this.requiredSkills = requiredSkills;
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
    sb.append("}");
    return sb.toString();
  }
}
