package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Algorithm   {
  

public enum ProblemTypeEnum {
  MIN("min");

  private String value;

  ProblemTypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  private ProblemTypeEnum problemType = null;

public enum ObjectiveEnum {
  TRANSPORT_TIME("transport_time"),
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

  private ObjectiveEnum objective = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("problem_type")
  public ProblemTypeEnum getProblemType() {
    return problemType;
  }
  public void setProblemType(ProblemTypeEnum problemType) {
    this.problemType = problemType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("objective")
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
