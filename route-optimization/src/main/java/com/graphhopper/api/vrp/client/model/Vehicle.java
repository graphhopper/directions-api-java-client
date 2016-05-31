package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Address;
import com.graphhopper.api.vrp.client.model.Break;
import java.util.*;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Vehicle   {
  
  private String vehicleId = null;
  private String typeId = null;
  private Address startAddress = null;
  private Address endAddress = null;
  private Break _break = null;
  private Boolean returnToDepot = null;
  private Long earliestStart = null;
  private Long latestEnd = null;
  private List<String> skills = new ArrayList<String>();

  
  /**
   * Unique identifier of vehicle
   **/
  @ApiModelProperty(value = "Unique identifier of vehicle")
  @JsonProperty("vehicle_id")
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
  @JsonProperty("type_id")
  public String getTypeId() {
    return typeId;
  }
  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("start_address")
  public Address getStartAddress() {
    return startAddress;
  }
  public void setStartAddress(Address startAddress) {
    this.startAddress = startAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("end_address")
  public Address getEndAddress() {
    return endAddress;
  }
  public void setEndAddress(Address endAddress) {
    this.endAddress = endAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("break")
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
  @JsonProperty("return_to_depot")
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
  @JsonProperty("earliest_start")
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
  @JsonProperty("latest_end")
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
  @JsonProperty("skills")
  public List<String> getSkills() {
    return skills;
  }
  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    vehicleId: ").append(StringUtil.toIndentedString(vehicleId)).append("\n");
    sb.append("    typeId: ").append(StringUtil.toIndentedString(typeId)).append("\n");
    sb.append("    startAddress: ").append(StringUtil.toIndentedString(startAddress)).append("\n");
    sb.append("    endAddress: ").append(StringUtil.toIndentedString(endAddress)).append("\n");
    sb.append("    _break: ").append(StringUtil.toIndentedString(_break)).append("\n");
    sb.append("    returnToDepot: ").append(StringUtil.toIndentedString(returnToDepot)).append("\n");
    sb.append("    earliestStart: ").append(StringUtil.toIndentedString(earliestStart)).append("\n");
    sb.append("    latestEnd: ").append(StringUtil.toIndentedString(latestEnd)).append("\n");
    sb.append("    skills: ").append(StringUtil.toIndentedString(skills)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
