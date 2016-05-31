package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
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
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeWindow {\n");
    
    sb.append("    earliest: ").append(StringUtil.toIndentedString(earliest)).append("\n");
    sb.append("    latest: ").append(StringUtil.toIndentedString(latest)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
