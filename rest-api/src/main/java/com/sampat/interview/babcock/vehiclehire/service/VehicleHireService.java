package com.sampat.interview.babcock.vehiclehire.service;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.model.VehicleHiringQuote;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VehicleHireService {
  /**
   * Finds Vehicles available to Hire Today.
   * returns list of vehicles available to hire today.
   */
  List<Vehicle> findVehiclesAvailableForHireToday();

  /**
   * Calculates cost of Hiring between dates.
   */
  VehicleHiringQuote calculateCostOfHiring(Long id, LocalDate startDate, LocalDate endDate);
}
