package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Break   {
  
  @SerializedName("earliest")
  private Long earliest = null;
  
  @SerializedName("latest")
  private Long latest = null;
  
  @SerializedName("duration")
  private Long duration = null;
  

  
  /**
   * earliest start of break
   **/
  @ApiModelProperty(value = "earliest start of break")
  public Long getEarliest() {
    return earliest;
  }
  public void setEarliest(Long earliest) {
    this.earliest = earliest;
  }

  
  /**
   * latest start of break
   **/
  @ApiModelProperty(value = "latest start of break")
  public Long getLatest() {
    return latest;
  }
  public void setLatest(Long latest) {
    this.latest = latest;
  }

  
  /**
   * duration of break
   **/
  @ApiModelProperty(value = "duration of break")
  public Long getDuration() {
    return duration;
  }
  public void setDuration(Long duration) {
    this.duration = duration;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Break _break = (Break) o;
    return Objects.equals(earliest, _break.earliest) &&
        Objects.equals(latest, _break.latest) &&
        Objects.equals(duration, _break.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(earliest, latest, duration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Break {\n");
    
    sb.append("    earliest: ").append(toIndentedString(earliest)).append("\n");
    sb.append("    latest: ").append(toIndentedString(latest)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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
