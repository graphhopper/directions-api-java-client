package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;





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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JobId jobId = (JobId) o;
    return Objects.equals(this.jobId, jobId.jobId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobId {\n");
    
    sb.append("    jobId: ").append(toIndentedString(jobId)).append("\n");
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
