package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Relation   {
  
  @SerializedName("type")
  private String type = null;
  
  @SerializedName("ids")
  private List<String> ids = new ArrayList<String>();
  
  @SerializedName("vehicle_id")
  private String vehicleId = null;
  

  
  /**
   * identifier of relation
   **/
  @ApiModelProperty(value = "identifier of relation")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * An array of ids that should be related
   **/
  @ApiModelProperty(value = "An array of ids that should be related")
  public List<String> getIds() {
    return ids;
  }
  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  
  /**
   * vehicle id
   **/
  @ApiModelProperty(value = "vehicle id")
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Relation relation = (Relation) o;
    return Objects.equals(type, relation.type) &&
        Objects.equals(ids, relation.ids) &&
        Objects.equals(vehicleId, relation.vehicleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, ids, vehicleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Relation {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    ids: ").append(toIndentedString(ids)).append("\n");
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
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
