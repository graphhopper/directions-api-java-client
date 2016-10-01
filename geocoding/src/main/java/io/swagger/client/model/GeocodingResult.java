package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Location;
import java.util.*;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class GeocodingResult   {
  
  @SerializedName("hits")
  private List<Location> hits = new ArrayList<Location>();
  
  @SerializedName("locale")
  private String locale = null;
  

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Location> getHits() {
    return hits;
  }
  public void setHits(List<Location> hits) {
    this.hits = hits;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getLocale() {
    return locale;
  }
  public void setLocale(String locale) {
    this.locale = locale;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeocodingResult geocodingResult = (GeocodingResult) o;
    return Objects.equals(hits, geocodingResult.hits) &&
        Objects.equals(locale, geocodingResult.locale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hits, locale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeocodingResult {\n");
    
    sb.append("    hits: ").append(toIndentedString(hits)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
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
