package com.graphhopper.routeopt.client.model;

import com.graphhopper.routeopt.client.StringUtil;
import com.graphhopper.routeopt.client.model.Relation;
import com.graphhopper.routeopt.client.model.Service;
import com.graphhopper.routeopt.client.model.Algorithm;
import com.graphhopper.routeopt.client.model.VehicleType;
import java.util.*;
import com.graphhopper.routeopt.client.model.Shipment;
import com.graphhopper.routeopt.client.model.Vehicle;

import com.google.gson.annotations.SerializedName;



import io.swagger.annotations.*;



@ApiModel(description = "")
public class Request   {
  
  @SerializedName("vehicles")
  private List<Vehicle> vehicles = new ArrayList<Vehicle>();
  
  @SerializedName("vehicle_types")
  private List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
  
  @SerializedName("services")
  private List<Service> services = new ArrayList<Service>();
  
  @SerializedName("shipments")
  private List<Shipment> shipments = new ArrayList<Shipment>();
  
  @SerializedName("relations")
  private List<Relation> relations = new ArrayList<Relation>();
  
  @SerializedName("algorithm")
  private Algorithm algorithm = null;
  

  
  /**
   * An array of vehicles that can be employed
   **/
  @ApiModelProperty(value = "An array of vehicles that can be employed")
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
  public List<Relation> getRelations() {
    return relations;
  }
  public void setRelations(List<Relation> relations) {
    this.relations = relations;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
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
