package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import java.util.*;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



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
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Relation {\n");
    
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    ids: ").append(StringUtil.toIndentedString(ids)).append("\n");
    sb.append("    vehicleId: ").append(StringUtil.toIndentedString(vehicleId)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
