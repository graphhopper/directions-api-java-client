package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import com.graphhopper.routeopt.client.model.Address;
import com.graphhopper.routeopt.client.model.Break;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Vehicle   {
  
  @SerializedName("vehicle_id")
  private String vehicleId = null;
  
  @SerializedName("type_id")
  private String typeId = null;
  
  @SerializedName("start_address")
  private Address startAddress = null;
  
  @SerializedName("end_address")
  private Address endAddress = null;
  
  @SerializedName("break")
  private Break _break = null;
  
  @SerializedName("return_to_depot")
  private Boolean returnToDepot = null;
  
  @SerializedName("earliest_start")
  private Long earliestStart = null;
  
  @SerializedName("latest_end")
  private Long latestEnd = null;
  
  @SerializedName("skills")
  private List<String> skills = new ArrayList<String>();
  

  
  /**
   * Unique identifier of vehicle
   **/
  @ApiModelProperty(value = "Unique identifier of vehicle")
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  
  /**
   * Unique identifier referring to the available vehicle types
   **/
  @ApiModelProperty(value = "Unique identifier referring to the available vehicle types")
  public String getTypeId() {
    return typeId;
  }
  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Address getStartAddress() {
    return startAddress;
  }
  public void setStartAddress(Address startAddress) {
    this.startAddress = startAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Address getEndAddress() {
    return endAddress;
  }
  public void setEndAddress(Address endAddress) {
    this.endAddress = endAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Break getBreak() {
    return _break;
  }
  public void setBreak(Break _break) {
    this._break = _break;
  }

  
  /**
   * Indicates whether vehicle should return to start address or not. If not, it can end at any service activity.
   **/
  @ApiModelProperty(value = "Indicates whether vehicle should return to start address or not. If not, it can end at any service activity.")
  public Boolean getReturnToDepot() {
    return returnToDepot;
  }
  public void setReturnToDepot(Boolean returnToDepot) {
    this.returnToDepot = returnToDepot;
  }

  
  /**
   * earliest start of vehicle at its start location
   **/
  @ApiModelProperty(value = "earliest start of vehicle at its start location")
  public Long getEarliestStart() {
    return earliestStart;
  }
  public void setEarliestStart(Long earliestStart) {
    this.earliestStart = earliestStart;
  }

  
  /**
   * latest end of vehicle at its end location
   **/
  @ApiModelProperty(value = "latest end of vehicle at its end location")
  public Long getLatestEnd() {
    return latestEnd;
  }
  public void setLatestEnd(Long latestEnd) {
    this.latestEnd = latestEnd;
  }

  
  /**
   * array of skills
   **/
  @ApiModelProperty(value = "array of skills")
  public List<String> getSkills() {
    return skills;
  }
  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(vehicleId, vehicle.vehicleId) &&
        Objects.equals(typeId, vehicle.typeId) &&
        Objects.equals(startAddress, vehicle.startAddress) &&
        Objects.equals(endAddress, vehicle.endAddress) &&
        Objects.equals(_break, vehicle._break) &&
        Objects.equals(returnToDepot, vehicle.returnToDepot) &&
        Objects.equals(earliestStart, vehicle.earliestStart) &&
        Objects.equals(latestEnd, vehicle.latestEnd) &&
        Objects.equals(skills, vehicle.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicleId, typeId, startAddress, endAddress, _break, returnToDepot, earliestStart, latestEnd, skills);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    startAddress: ").append(toIndentedString(startAddress)).append("\n");
    sb.append("    endAddress: ").append(toIndentedString(endAddress)).append("\n");
    sb.append("    _break: ").append(toIndentedString(_break)).append("\n");
    sb.append("    returnToDepot: ").append(toIndentedString(returnToDepot)).append("\n");
    sb.append("    earliestStart: ").append(toIndentedString(earliestStart)).append("\n");
    sb.append("    latestEnd: ").append(toIndentedString(latestEnd)).append("\n");
    sb.append("    skills: ").append(toIndentedString(skills)).append("\n");
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
