package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Algorithm   {
  

public enum ProblemTypeEnum {
  @SerializedName("min")
  MIN("min"),

  @SerializedName("min-max")
  MIN_MAX("min-max");

  private String value;

  ProblemTypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("problem_type")
  private ProblemTypeEnum problemType = null;
  

public enum ObjectiveEnum {
  @SerializedName("transport_time")
  TRANSPORT_TIME("transport_time"),

  @SerializedName("completion_time")
  COMPLETION_TIME("completion_time");

  private String value;

  ObjectiveEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("objective")
  private ObjectiveEnum objective = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public ProblemTypeEnum getProblemType() {
    return problemType;
  }
  public void setProblemType(ProblemTypeEnum problemType) {
    this.problemType = problemType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public ObjectiveEnum getObjective() {
    return objective;
  }
  public void setObjective(ObjectiveEnum objective) {
    this.objective = objective;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Algorithm {\n");
    
    sb.append("    problemType: ").append(StringUtil.toIndentedString(problemType)).append("\n");
    sb.append("    objective: ").append(StringUtil.toIndentedString(objective)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
