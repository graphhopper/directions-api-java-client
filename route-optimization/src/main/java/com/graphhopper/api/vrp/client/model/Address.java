package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Address   {
  
  private String locationId = null;
  private Double lon = null;
  private Double lat = null;

  
  /**
   * Unique identifier of location
   **/
  @ApiModelProperty(value = "Unique identifier of location")
  @JsonProperty("location_id")
  public String getLocationId() {
    return locationId;
  }
  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  
  /**
   * longitude
   **/
  @ApiModelProperty(value = "longitude")
  @JsonProperty("lon")
  public Double getLon() {
    return lon;
  }
  public void setLon(Double lon) {
    this.lon = lon;
  }

  
  /**
   * latitude
   **/
  @ApiModelProperty(value = "latitude")
  @JsonProperty("lat")
  public Double getLat() {
    return lat;
  }
  public void setLat(Double lat) {
    this.lat = lat;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    locationId: ").append(StringUtil.toIndentedString(locationId)).append("\n");
    sb.append("    lon: ").append(StringUtil.toIndentedString(lon)).append("\n");
    sb.append("    lat: ").append(StringUtil.toIndentedString(lat)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
