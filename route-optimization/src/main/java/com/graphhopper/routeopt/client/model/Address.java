package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Address   {
  
  @SerializedName("location_id")
  private String locationId = null;
  
  @SerializedName("lon")
  private Double lon = null;
  
  @SerializedName("lat")
  private Double lat = null;
  

  
  /**
   * Unique identifier of location
   **/
  @ApiModelProperty(value = "Unique identifier of location")
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
