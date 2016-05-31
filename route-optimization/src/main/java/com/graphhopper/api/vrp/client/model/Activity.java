package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Activity   {
  

public enum TypeEnum {
  START("start"),
  END("end"),
  SERVICE("service"),
  PICKUPSHIPMENT("pickupShipment"),
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

  private TypeEnum type = null;
  private String id = null;
  private String locationId = null;
  private Long arrTime = null;
  private Long endTime = null;

  
  /**
   * type of activity
   **/
  @ApiModelProperty(value = "type of activity")
  @JsonProperty("type")
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
  @JsonProperty("id")
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
  @JsonProperty("location_id")
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
  @JsonProperty("arr_time")
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
  @JsonProperty("end_time")
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
