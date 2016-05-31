package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Solution;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Response   {
  
  private String jobId = null;

public enum StatusEnum {
  WAITING_IN_QUEUE("waiting_in_queue"),
  PROCESSING("processing"),
  FINISHED("finished");

  private String value;

  StatusEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  private StatusEnum status = null;
  private Long waitingInQueue = null;
  private Long processingTime = null;
  private Solution solution = null;

  
  /**
   * unique identify of job - which you get when posting your request to the large problem solver
   **/
  @ApiModelProperty(value = "unique identify of job - which you get when posting your request to the large problem solver")
  @JsonProperty("job_id")
  public String getJobId() {
    return jobId;
  }
  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  
  /**
   * indicates the current status of the job
   **/
  @ApiModelProperty(value = "indicates the current status of the job")
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  
  /**
   * waiting time in ms
   **/
  @ApiModelProperty(value = "waiting time in ms")
  @JsonProperty("waiting_in_queue")
  public Long getWaitingInQueue() {
    return waitingInQueue;
  }
  public void setWaitingInQueue(Long waitingInQueue) {
    this.waitingInQueue = waitingInQueue;
  }

  
  /**
   * processing time in ms. if job is still waiting in queue, processing_time is 0
   **/
  @ApiModelProperty(value = "processing time in ms. if job is still waiting in queue, processing_time is 0")
  @JsonProperty("processing_time")
  public Long getProcessingTime() {
    return processingTime;
  }
  public void setProcessingTime(Long processingTime) {
    this.processingTime = processingTime;
  }

  
  /**
   * the solution. only available if status field indicates finished
   **/
  @ApiModelProperty(value = "the solution. only available if status field indicates finished")
  @JsonProperty("solution")
  public Solution getSolution() {
    return solution;
  }
  public void setSolution(Solution solution) {
    this.solution = solution;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    
    sb.append("    jobId: ").append(StringUtil.toIndentedString(jobId)).append("\n");
    sb.append("    status: ").append(StringUtil.toIndentedString(status)).append("\n");
    sb.append("    waitingInQueue: ").append(StringUtil.toIndentedString(waitingInQueue)).append("\n");
    sb.append("    processingTime: ").append(StringUtil.toIndentedString(processingTime)).append("\n");
    sb.append("    solution: ").append(StringUtil.toIndentedString(solution)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
