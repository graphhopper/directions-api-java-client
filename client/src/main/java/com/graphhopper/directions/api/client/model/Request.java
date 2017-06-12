/*
 * GraphHopper Directions API
 * You use the GraphHopper Directions API to add route planning, navigation and route optimization to your software. E.g. the Routing API has turn instructions and elevation data and the Route Optimization API solves your logistic problems and supports various constraints like time window and capacity restrictions. Also it is possible to get all distances between all locations with our fast Matrix API.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.graphhopper.directions.api.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import com.graphhopper.directions.api.client.model.Algorithm;
import com.graphhopper.directions.api.client.model.CostMatrix;
import com.graphhopper.directions.api.client.model.ModelConfiguration;
import com.graphhopper.directions.api.client.model.Objective;
import com.graphhopper.directions.api.client.model.Relation;
import com.graphhopper.directions.api.client.model.Service;
import com.graphhopper.directions.api.client.model.Shipment;
import com.graphhopper.directions.api.client.model.Vehicle;
import com.graphhopper.directions.api.client.model.VehicleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Request
 */

public class Request {
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

  @SerializedName("objectives")
  private List<Objective> objectives = new ArrayList<Objective>();

  @SerializedName("cost_matrices")
  private List<CostMatrix> costMatrices = new ArrayList<CostMatrix>();

  @SerializedName("configuration")
  private ModelConfiguration _configuration = null;

  public Request vehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
    return this;
  }

  public Request addVehiclesItem(Vehicle vehiclesItem) {
    this.vehicles.add(vehiclesItem);
    return this;
  }

   /**
   * An array of vehicles that can be employed
   * @return vehicles
  **/
  @ApiModelProperty(example = "null", value = "An array of vehicles that can be employed")
  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public Request vehicleTypes(List<VehicleType> vehicleTypes) {
    this.vehicleTypes = vehicleTypes;
    return this;
  }

  public Request addVehicleTypesItem(VehicleType vehicleTypesItem) {
    this.vehicleTypes.add(vehicleTypesItem);
    return this;
  }

   /**
   * An array of vehicle types
   * @return vehicleTypes
  **/
  @ApiModelProperty(example = "null", value = "An array of vehicle types")
  public List<VehicleType> getVehicleTypes() {
    return vehicleTypes;
  }

  public void setVehicleTypes(List<VehicleType> vehicleTypes) {
    this.vehicleTypes = vehicleTypes;
  }

  public Request services(List<Service> services) {
    this.services = services;
    return this;
  }

  public Request addServicesItem(Service servicesItem) {
    this.services.add(servicesItem);
    return this;
  }

   /**
   * An array of services
   * @return services
  **/
  @ApiModelProperty(example = "null", value = "An array of services")
  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  public Request shipments(List<Shipment> shipments) {
    this.shipments = shipments;
    return this;
  }

  public Request addShipmentsItem(Shipment shipmentsItem) {
    this.shipments.add(shipmentsItem);
    return this;
  }

   /**
   * An array of shipments
   * @return shipments
  **/
  @ApiModelProperty(example = "null", value = "An array of shipments")
  public List<Shipment> getShipments() {
    return shipments;
  }

  public void setShipments(List<Shipment> shipments) {
    this.shipments = shipments;
  }

  public Request relations(List<Relation> relations) {
    this.relations = relations;
    return this;
  }

  public Request addRelationsItem(Relation relationsItem) {
    this.relations.add(relationsItem);
    return this;
  }

   /**
   * An array of relations
   * @return relations
  **/
  @ApiModelProperty(example = "null", value = "An array of relations")
  public List<Relation> getRelations() {
    return relations;
  }

  public void setRelations(List<Relation> relations) {
    this.relations = relations;
  }

  public Request algorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
    return this;
  }

   /**
   * Get algorithm
   * @return algorithm
  **/
  @ApiModelProperty(example = "null", value = "")
  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  public Request objectives(List<Objective> objectives) {
    this.objectives = objectives;
    return this;
  }

  public Request addObjectivesItem(Objective objectivesItem) {
    this.objectives.add(objectivesItem);
    return this;
  }

   /**
   * An array of objectives
   * @return objectives
  **/
  @ApiModelProperty(example = "null", value = "An array of objectives")
  public List<Objective> getObjectives() {
    return objectives;
  }

  public void setObjectives(List<Objective> objectives) {
    this.objectives = objectives;
  }

  public Request costMatrices(List<CostMatrix> costMatrices) {
    this.costMatrices = costMatrices;
    return this;
  }

  public Request addCostMatricesItem(CostMatrix costMatricesItem) {
    this.costMatrices.add(costMatricesItem);
    return this;
  }

   /**
   * An array of cost matrices
   * @return costMatrices
  **/
  @ApiModelProperty(example = "null", value = "An array of cost matrices")
  public List<CostMatrix> getCostMatrices() {
    return costMatrices;
  }

  public void setCostMatrices(List<CostMatrix> costMatrices) {
    this.costMatrices = costMatrices;
  }

  public Request _configuration(ModelConfiguration _configuration) {
    this._configuration = _configuration;
    return this;
  }

   /**
   * Get _configuration
   * @return _configuration
  **/
  @ApiModelProperty(example = "null", value = "")
  public ModelConfiguration getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(ModelConfiguration _configuration) {
    this._configuration = _configuration;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Request request = (Request) o;
    return Objects.equals(this.vehicles, request.vehicles) &&
        Objects.equals(this.vehicleTypes, request.vehicleTypes) &&
        Objects.equals(this.services, request.services) &&
        Objects.equals(this.shipments, request.shipments) &&
        Objects.equals(this.relations, request.relations) &&
        Objects.equals(this.algorithm, request.algorithm) &&
        Objects.equals(this.objectives, request.objectives) &&
        Objects.equals(this.costMatrices, request.costMatrices) &&
        Objects.equals(this._configuration, request._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicles, vehicleTypes, services, shipments, relations, algorithm, objectives, costMatrices, _configuration);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Request {\n");
    
    sb.append("    vehicles: ").append(toIndentedString(vehicles)).append("\n");
    sb.append("    vehicleTypes: ").append(toIndentedString(vehicleTypes)).append("\n");
    sb.append("    services: ").append(toIndentedString(services)).append("\n");
    sb.append("    shipments: ").append(toIndentedString(shipments)).append("\n");
    sb.append("    relations: ").append(toIndentedString(relations)).append("\n");
    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("    objectives: ").append(toIndentedString(objectives)).append("\n");
    sb.append("    costMatrices: ").append(toIndentedString(costMatrices)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

