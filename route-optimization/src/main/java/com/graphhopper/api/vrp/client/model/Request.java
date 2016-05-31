package com.graphhopper.api.vrp.client.model;

import com.graphhopper.api.vrp.client.StringUtil;
import com.graphhopper.api.vrp.client.model.Vehicle;
import com.graphhopper.api.vrp.client.model.Relation;
import com.graphhopper.api.vrp.client.model.VehicleType;
import com.graphhopper.api.vrp.client.model.Service;
import com.graphhopper.api.vrp.client.model.Shipment;
import java.util.*;
import com.graphhopper.api.vrp.client.model.Algorithm;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-19T11:09:20.969+02:00")
public class Request   {
  
  private List<Vehicle> vehicles = new ArrayList<Vehicle>();
  private List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
  private List<Service> services = new ArrayList<Service>();
  private List<Shipment> shipments = new ArrayList<Shipment>();
  private List<Relation> relations = new ArrayList<Relation>();
  private Algorithm algorithm = null;

  
  /**
   * An array of vehicles that can be employed
   **/
  @ApiModelProperty(value = "An array of vehicles that can be employed")
  @JsonProperty("vehicles")
  public List<Vehicle> getVehicles() {
    return vehicles;
  }
  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  
  /**
   * An array of vehicle types
   **/
  @ApiModelProperty(value = "An array of vehicle types")
  @JsonProperty("vehicle_types")
  public List<VehicleType> getVehicleTypes() {
    return vehicleTypes;
  }
  public void setVehicleTypes(List<VehicleType> vehicleTypes) {
    this.vehicleTypes = vehicleTypes;
  }

  
  /**
   * An array of services
   **/
  @ApiModelProperty(value = "An array of services")
  @JsonProperty("services")
  public List<Service> getServices() {
    return services;
  }
  public void setServices(List<Service> services) {
    this.services = services;
  }

  
  /**
   * An array of shipments
   **/
  @ApiModelProperty(value = "An array of shipments")
  @JsonProperty("shipments")
  public List<Shipment> getShipments() {
    return shipments;
  }
  public void setShipments(List<Shipment> shipments) {
    this.shipments = shipments;
  }

  
  /**
   * An array of relations
   **/
  @ApiModelProperty(value = "An array of relations")
  @JsonProperty("relations")
  public List<Relation> getRelations() {
    return relations;
  }
  public void setRelations(List<Relation> relations) {
    this.relations = relations;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("algorithm")
  public Algorithm getAlgorithm() {
    return algorithm;
  }
  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Request {\n");
    
    sb.append("    vehicles: ").append(StringUtil.toIndentedString(vehicles)).append("\n");
    sb.append("    vehicleTypes: ").append(StringUtil.toIndentedString(vehicleTypes)).append("\n");
    sb.append("    services: ").append(StringUtil.toIndentedString(services)).append("\n");
    sb.append("    shipments: ").append(StringUtil.toIndentedString(shipments)).append("\n");
    sb.append("    relations: ").append(StringUtil.toIndentedString(relations)).append("\n");
    sb.append("    algorithm: ").append(StringUtil.toIndentedString(algorithm)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
