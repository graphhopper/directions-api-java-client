package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Point;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Location   {
  
  @SerializedName("point")
  private Point point = null;
  
  @SerializedName("osm_id")
  private String osmId = null;
  
  @SerializedName("osm_type")
  private String osmType = null;
  
  @SerializedName("name")
  private String name = null;
  
  @SerializedName("country")
  private String country = null;
  
  @SerializedName("city")
  private String city = null;
  
  @SerializedName("state")
  private String state = null;
  
  @SerializedName("street")
  private String street = null;
  
  @SerializedName("housenumber")
  private String housenumber = null;
  
  @SerializedName("postcode")
  private String postcode = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Point getPoint() {
    return point;
  }
  public void setPoint(Point point) {
    this.point = point;
  }

  
  /**
   * OSM Id
   **/
  @ApiModelProperty(value = "OSM Id")
  public String getOsmId() {
    return osmId;
  }
  public void setOsmId(String osmId) {
    this.osmId = osmId;
  }

  
  /**
   * N = node, R = relation, W = way
   **/
  @ApiModelProperty(value = "N = node, R = relation, W = way")
  public String getOsmType() {
    return osmType;
  }
  public void setOsmType(String osmType) {
    this.osmType = osmType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getStreet() {
    return street;
  }
  public void setStreet(String street) {
    this.street = street;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getHousenumber() {
    return housenumber;
  }
  public void setHousenumber(String housenumber) {
    this.housenumber = housenumber;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getPostcode() {
    return postcode;
  }
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(point, location.point) &&
        Objects.equals(osmId, location.osmId) &&
        Objects.equals(osmType, location.osmType) &&
        Objects.equals(name, location.name) &&
        Objects.equals(country, location.country) &&
        Objects.equals(city, location.city) &&
        Objects.equals(state, location.state) &&
        Objects.equals(street, location.street) &&
        Objects.equals(housenumber, location.housenumber) &&
        Objects.equals(postcode, location.postcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(point, osmId, osmType, name, country, city, state, street, housenumber, postcode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    
    sb.append("    point: ").append(toIndentedString(point)).append("\n");
    sb.append("    osmId: ").append(toIndentedString(osmId)).append("\n");
    sb.append("    osmType: ").append(toIndentedString(osmType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    housenumber: ").append(toIndentedString(housenumber)).append("\n");
    sb.append("    postcode: ").append(toIndentedString(postcode)).append("\n");
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
