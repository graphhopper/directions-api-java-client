package io.swagger.client.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;





public class SolutionUnassigned   {
  
  @SerializedName("services")
  private List<String> services = new ArrayList<String>();
  
  @SerializedName("shipments")
  private List<String> shipments = new ArrayList<String>();
  

  
  /**
   * An array of ids of unassigned services
   **/
  @ApiModelProperty(value = "An array of ids of unassigned services")
  public List<String> getServices() {
    return services;
  }
  public void setServices(List<String> services) {
    this.services = services;
  }

  
  /**
   * An array of ids of unassigned shipments
   **/
  @ApiModelProperty(value = "An array of ids of unassigned shipments")
  public List<String> getShipments() {
    return shipments;
  }
  public void setShipments(List<String> shipments) {
    this.shipments = shipments;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SolutionUnassigned solutionUnassigned = (SolutionUnassigned) o;
    return Objects.equals(this.services, solutionUnassigned.services) &&
        Objects.equals(this.shipments, solutionUnassigned.shipments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(services, shipments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SolutionUnassigned {\n");
    
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
    sb.append("    shipments: ").append(toIndentedString(shipments)).append("\n");
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
