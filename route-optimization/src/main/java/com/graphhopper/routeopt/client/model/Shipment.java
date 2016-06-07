package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Stop;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





public class Shipment   {
  
  @SerializedName("id")
  private String id = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("priority")
  private Integer priority = null;
  
  @SerializedName("pickup")
  private Stop pickup = null;
  
  @SerializedName("delivery")
  private Stop delivery = null;
  
  @SerializedName("size")
  private List<Integer> size = new ArrayList<Integer>();
  
  @SerializedName("required_skills")
  private List<String> requiredSkills = new ArrayList<String>();
  
  @SerializedName("allowed_vehicles")
  private List<String> allowedVehicles = new ArrayList<String>();
  

  
  /**
   * Unique identifier of service
   **/
  @ApiModelProperty(value = "Unique identifier of service")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * name of shipment
   **/
  @ApiModelProperty(value = "name of shipment")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * priority of service, i.e. 1 = high, 2 = normal, 3 = low. default is 2.
   **/
  @ApiModelProperty(value = "priority of service, i.e. 1 = high, 2 = normal, 3 = low. default is 2.")
  public Integer getPriority() {
    return priority;
  }
  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Stop getPickup() {
    return pickup;
  }
  public void setPickup(Stop pickup) {
    this.pickup = pickup;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Stop getDelivery() {
    return delivery;
  }
  public void setDelivery(Stop delivery) {
    this.delivery = delivery;
  }

  
  /**
   * array of capacity dimensions
   **/
  @ApiModelProperty(value = "array of capacity dimensions")
  public List<Integer> getSize() {
    return size;
  }
  public void setSize(List<Integer> size) {
    this.size = size;
  }

  
  /**
   * array of required skills
   **/
  @ApiModelProperty(value = "array of required skills")
  public List<String> getRequiredSkills() {
    return requiredSkills;
  }
  public void setRequiredSkills(List<String> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  
  /**
   * array of allowed vehicle ids
   **/
  @ApiModelProperty(value = "array of allowed vehicle ids")
  public List<String> getAllowedVehicles() {
    return allowedVehicles;
  }
  public void setAllowedVehicles(List<String> allowedVehicles) {
    this.allowedVehicles = allowedVehicles;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shipment shipment = (Shipment) o;
    return Objects.equals(this.id, shipment.id) &&
        Objects.equals(this.name, shipment.name) &&
        Objects.equals(this.priority, shipment.priority) &&
        Objects.equals(this.pickup, shipment.pickup) &&
        Objects.equals(this.delivery, shipment.delivery) &&
        Objects.equals(this.size, shipment.size) &&
        Objects.equals(this.requiredSkills, shipment.requiredSkills) &&
        Objects.equals(this.allowedVehicles, shipment.allowedVehicles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, priority, pickup, delivery, size, requiredSkills, allowedVehicles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Shipment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    pickup: ").append(toIndentedString(pickup)).append("\n");
    sb.append("    delivery: ").append(toIndentedString(delivery)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    requiredSkills: ").append(toIndentedString(requiredSkills)).append("\n");
    sb.append("    allowedVehicles: ").append(toIndentedString(allowedVehicles)).append("\n");
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
