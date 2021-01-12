package com.sampat.interview.babcock.vehiclehire.service;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import java.util.List;

public interface VehicleHireService {
  /**
   * Finds Vehicles available to Hire Today.
   * returns list of vehicles available to hire today.
   */
  List<Vehicle> findVehiclesAvailableForHireToday();
}
