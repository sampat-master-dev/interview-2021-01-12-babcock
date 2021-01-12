package com.sampat.interview.babcock.vehiclehire.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;

public final class AssertionUtility {

  public static void assertVehicle(Vehicle vehicle, String registrationNumber, String vehicleCategory,
    String make, String model, String fuelType) {
    assertNotNull(vehicle);
    assertEquals(registrationNumber, vehicle.getRegistrationNumber());
    assertEquals(vehicleCategory, vehicle.getVehicleCategory());
    assertEquals(make, vehicle.getMake());
    assertEquals(model, vehicle.getModel());
    assertEquals(fuelType, vehicle.getFuelType());
  }
}
