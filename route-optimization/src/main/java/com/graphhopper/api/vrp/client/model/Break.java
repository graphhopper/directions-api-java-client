package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Break   {
  
  private Long earliest = null;
  private Long latest = null;
  private Long duration = null;

  
  /**
   * earliest start of break
   **/
  @ApiModelProperty(value = "earliest start of break")
  @JsonProperty("earliest")
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
  @JsonProperty("latest")
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
  @JsonProperty("duration")
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
