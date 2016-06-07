package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.Activity;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





public class Route   {
  
  @SerializedName("vehicle_id")
  private String vehicleId = null;
  
  @SerializedName("activities")
  private List<Activity> activities = new ArrayList<Activity>();
  

  
  /**
   * id of vehicle that operates route
   **/
  @ApiModelProperty(value = "id of vehicle that operates route")
  public String getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  
  /**
   * array of activities
   **/
  @ApiModelProperty(value = "array of activities")
  public List<Activity> getActivities() {
    return activities;
  }
  public void setActivities(List<Activity> activities) {
    this.activities = activities;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Route route = (Route) o;
    return Objects.equals(this.vehicleId, route.vehicleId) &&
        Objects.equals(this.activities, route.activities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicleId, activities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Route {\n");
    
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
    sb.append("    activities: ").append(toIndentedString(activities)).append("\n");
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
