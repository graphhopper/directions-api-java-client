package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;





public class TimeWindow   {
  
  @SerializedName("earliest")
  private Long earliest = null;
  
  @SerializedName("latest")
  private Long latest = null;
  

  
  /**
   * earliest start time of corresponding activity
   **/
  @ApiModelProperty(value = "earliest start time of corresponding activity")
  public Long getEarliest() {
    return earliest;
  }
  public void setEarliest(Long earliest) {
    this.earliest = earliest;
  }

  
  /**
   * latest start time of corresponding activity
   **/
  @ApiModelProperty(value = "latest start time of corresponding activity")
  public Long getLatest() {
    return latest;
  }
  public void setLatest(Long latest) {
    this.latest = latest;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeWindow timeWindow = (TimeWindow) o;
    return Objects.equals(this.earliest, timeWindow.earliest) &&
        Objects.equals(this.latest, timeWindow.latest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(earliest, latest);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeWindow {\n");
    
    sb.append("    earliest: ").append(toIndentedString(earliest)).append("\n");
    sb.append("    latest: ").append(toIndentedString(latest)).append("\n");
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
