package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Address;
import java.util.*;
import com.graphhopper.api.vrp.client.model.TimeWindow;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Stop   {
  
  private Address address = null;
  private Long duration = null;
  private List<TimeWindow> timeWindows = new ArrayList<TimeWindow>();

  
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
   * duration of stop, i.e. time in ms the corresponding activity takes
   **/
  @ApiModelProperty(value = "duration of stop, i.e. time in ms the corresponding activity takes")
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stop {\n");
    
    sb.append("    address: ").append(StringUtil.toIndentedString(address)).append("\n");
    sb.append("    duration: ").append(StringUtil.toIndentedString(duration)).append("\n");
    sb.append("    timeWindows: ").append(StringUtil.toIndentedString(timeWindows)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
