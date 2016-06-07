package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import com.graphhopper.routeopt.client.model.Address;
import com.graphhopper.routeopt.client.model.TimeWindow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;

import com.google.gson.annotations.SerializedName;




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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stop stop = (Stop) o;
    return Objects.equals(address, stop.address) &&
        Objects.equals(duration, stop.duration) &&
        Objects.equals(timeWindows, stop.timeWindows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, duration, timeWindows);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stop {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    timeWindows: ").append(toIndentedString(timeWindows)).append("\n");
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
