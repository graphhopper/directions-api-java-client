package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class TimeWindow   {
  
  private Long earliest = null;
  private Long latest = null;

  
  /**
   * earliest start time of corresponding activity
   **/
  @ApiModelProperty(value = "earliest start time of corresponding activity")
  @JsonProperty("earliest")
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
  @JsonProperty("latest")
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
