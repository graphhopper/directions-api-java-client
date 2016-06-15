package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;





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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Algorithm algorithm = (Algorithm) o;
    return Objects.equals(this.problemType, algorithm.problemType) &&
        Objects.equals(this.objective, algorithm.objective);
  }

  @Override
  public int hashCode() {
    return Objects.hash(problemType, objective);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Algorithm {\n");
    
    sb.append("    problemType: ").append(toIndentedString(problemType)).append("\n");
    sb.append("    objective: ").append(toIndentedString(objective)).append("\n");
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
