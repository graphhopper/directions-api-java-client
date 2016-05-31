package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class JobId   {
  
  private String jobId = null;

  
  /**
   * unique id for your job/request with which you can fetch your solution
   **/
  @ApiModelProperty(value = "unique id for your job/request with which you can fetch your solution")
  @JsonProperty("job_id")
  public String getJobId() {
    return jobId;
  }
  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobId {\n");
    
    sb.append("    jobId: ").append(StringUtil.toIndentedString(jobId)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
