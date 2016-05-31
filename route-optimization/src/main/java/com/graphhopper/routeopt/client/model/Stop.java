package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import com.graphhopper.routeopt.client.model.TimeWindow;
import com.graphhopper.routeopt.client.model.Address;
import java.util.*;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Stop   {
  
  @SerializedName("address")
  private Address address = null;
  
  @SerializedName("duration")
  private Long duration = null;
  
  @SerializedName("time_windows")
  private List<TimeWindow> timeWindows = new ArrayList<TimeWindow>();
  

  
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
   * duration of stop, i.e. time in ms the corresponding activity takes
   **/
  @ApiModelProperty(value = "duration of stop, i.e. time in ms the corresponding activity takes")
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
