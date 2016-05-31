package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Activity   {
  

public enum TypeEnum {
  @SerializedName("start")
  START("start"),

  @SerializedName("end")
  END("end"),

  @SerializedName("service")
  SERVICE("service"),

  @SerializedName("pickupShipment")
  PICKUPSHIPMENT("pickupShipment"),

  @SerializedName("deliverShipment")
  DELIVERSHIPMENT("deliverShipment");

  private String value;

  TypeEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

  @SerializedName("type")
  private TypeEnum type = null;
  
  @SerializedName("id")
  private String id = null;
  
  @SerializedName("location_id")
  private String locationId = null;
  
  @SerializedName("arr_time")
  private Long arrTime = null;
  
  @SerializedName("end_time")
  private Long endTime = null;
  

  
  /**
   * type of activity
   **/
  @ApiModelProperty(value = "type of activity")
  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }

  
  /**
   * id referring to the underlying service or shipment, i.e. the shipment or service this activity belongs to
   **/
  @ApiModelProperty(value = "id referring to the underlying service or shipment, i.e. the shipment or service this activity belongs to")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * id that refers to address
   **/
  @ApiModelProperty(value = "id that refers to address")
  public String getLocationId() {
    return locationId;
  }
  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  
  /**
   * arrival time at this activity in ms
   **/
  @ApiModelProperty(value = "arrival time at this activity in ms")
  public Long getArrTime() {
    return arrTime;
  }
  public void setArrTime(Long arrTime) {
    this.arrTime = arrTime;
  }

  
  /**
   * end time of and thus departure time at this activity
   **/
  @ApiModelProperty(value = "end time of and thus departure time at this activity")
  public Long getEndTime() {
    return endTime;
  }
  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Activity {\n");
    
    sb.append("    type: ").append(StringUtil.toIndentedString(type)).append("\n");
    sb.append("    id: ").append(StringUtil.toIndentedString(id)).append("\n");
    sb.append("    locationId: ").append(StringUtil.toIndentedString(locationId)).append("\n");
    sb.append("    arrTime: ").append(StringUtil.toIndentedString(arrTime)).append("\n");
    sb.append("    endTime: ").append(StringUtil.toIndentedString(endTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
