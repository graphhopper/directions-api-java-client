package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class JobId   {
  
  @SerializedName("job_id")
  private String jobId = null;
  

  
  /**
   * unique id for your job/request with which you can fetch your solution
   **/
  @ApiModelProperty(value = "unique id for your job/request with which you can fetch your solution")
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
