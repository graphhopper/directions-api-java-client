package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



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
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Break {\n");
    
    sb.append("    earliest: ").append(StringUtil.toIndentedString(earliest)).append("\n");
    sb.append("    latest: ").append(StringUtil.toIndentedString(latest)).append("\n");
    sb.append("    duration: ").append(StringUtil.toIndentedString(duration)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
