package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import com.graphhopper.routeopt.client.model.Solution;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Response   {
  
  @SerializedName("job_id")
  private String jobId = null;
  

public enum StatusEnum {
  @SerializedName("waiting_in_queue")
  WAITING_IN_QUEUE("waiting_in_queue"),

  @SerializedName("processing")
  PROCESSING("processing"),

  @SerializedName("finished")
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

  @SerializedName("status")
  private StatusEnum status = null;
  
  @SerializedName("waiting_in_queue")
  private Long waitingInQueue = null;
  
  @SerializedName("processing_time")
  private Long processingTime = null;
  
  @SerializedName("solution")
  private Solution solution = null;
  

  
  /**
   * unique identify of job - which you get when posting your request to the large problem solver
   **/
  @ApiModelProperty(value = "unique identify of job - which you get when posting your request to the large problem solver")
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
