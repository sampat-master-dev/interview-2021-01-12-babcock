package com.sampat.interview.babcock.vehiclehire.service;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleHireServiceImpl implements VehicleHireService {
  @Autowired
  private Clock clock;

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  /**
   * Finds Vehicles available to Hire Today. returns list of vehicles available to hire today.
   */
  @Override
  public List<Vehicle> findVehiclesAvailableForHireToday() {
    return vehicleHireRepository.getVehiclesThatCanBeHiredToday(LocalDate.now(clock));
  }
}
