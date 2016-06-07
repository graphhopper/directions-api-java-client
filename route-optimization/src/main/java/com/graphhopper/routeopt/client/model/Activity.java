package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activity activity = (Activity) o;
    return Objects.equals(type, activity.type) &&
        Objects.equals(id, activity.id) &&
        Objects.equals(locationId, activity.locationId) &&
        Objects.equals(arrTime, activity.arrTime) &&
        Objects.equals(endTime, activity.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, locationId, arrTime, endTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Activity {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    arrTime: ").append(toIndentedString(arrTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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
