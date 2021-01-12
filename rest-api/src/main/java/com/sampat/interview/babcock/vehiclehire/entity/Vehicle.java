package com.sampat.interview.babcock.vehiclehire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="registration_number")
  private String registrationNumber;

  @Column(name="category")
  private String vehicleCategory;

  @Column(name="make")
  private String make;

  @Column(name="model")
  private String model;

  @Column(name="fuel_type")
  private String fuelType;

  public Vehicle(String registrationNumber, String vehicleCategory, String make, String model, String fuelType){
    this.registrationNumber = registrationNumber;
    this.vehicleCategory = vehicleCategory;
    this.make = make;
    this.model = model;
    this.fuelType = fuelType;
  }
}