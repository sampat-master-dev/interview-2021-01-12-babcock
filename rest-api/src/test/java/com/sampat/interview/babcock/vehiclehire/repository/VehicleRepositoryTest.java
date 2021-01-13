package com.sampat.interview.babcock.vehiclehire.repository;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class VehicleRepositoryTest {
  @Autowired
  private VehicleRepository vehicleRepository;

  @BeforeEach
  public void setup() {
    log.info("in setup");
    vehicleRepository.deleteAll();
    vehicleRepository.flush();
  }

  @Test
  public void testAndValidateVehicleSave() {
    vehicleRepository
      .save(new Vehicle("AB07KYC", "Small car", "Toyota", "Avensis", "Petrol"));

    List<Vehicle> vehicleList = vehicleRepository.findByRegistrationNumber("AB07KYC");

    assertNotNull(vehicleList);
    assertEquals(1, vehicleList.size());

    Vehicle vehicle = vehicleList.get(0);
    assertVehicle(vehicle, "AB07KYC", "Small car", "Toyota", "Avensis", "Petrol");
  }

  @Test
  public void testAndValidateMultipleSave() {
    vehicleRepository.save(new Vehicle("BC11KYC", "Small car", "Toyota", "Avensis", "Petrol"));
    vehicleRepository.save(new Vehicle("BD12KYC", "Van", "Toyota", "Avensis", "Petrol"));
    List<Vehicle> vehicleList = vehicleRepository.findAll();

    assertNotNull(vehicleList);
    assertEquals(2, vehicleList.size());
    assertVehicle(vehicleList.get(0), "BC11KYC", "Small car", "Toyota", "Avensis", "Petrol");
    assertVehicle(vehicleList.get(1), "BD12KYC", "Van", "Toyota", "Avensis", "Petrol");
  }
}