package com.graphhopper.routeopt.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;





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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.locationId, address.locationId) &&
        Objects.equals(this.lon, address.lon) &&
        Objects.equals(this.lat, address.lat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationId, lon, lat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
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
