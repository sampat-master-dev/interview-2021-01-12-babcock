package com.sampat.interview.babcock.vehiclehire.service;

import static java.time.temporal.ChronoUnit.DAYS;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleCategoryPrice;
import com.sampat.interview.babcock.vehiclehire.exception.VehicleHireServiceException;
import com.sampat.interview.babcock.vehiclehire.exception.VehicleNotFoundException;
import com.sampat.interview.babcock.vehiclehire.model.VehicleHiringQuote;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleCategoryPriceRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleRepository;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VehicleHireServiceImpl implements VehicleHireService {
  @Autowired
  private Clock clock;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  @Autowired
  private VehicleCategoryPriceRepository vehicleCategoryPriceRepository;

  /**
   * Finds Vehicles available to Hire Today. returns list of vehicles available to hire today.
   */
  @Override
  public List<Vehicle> findVehiclesAvailableForHireToday() {
    return vehicleHireRepository.getVehiclesThatCanBeHiredToday(LocalDate.now(clock));
  }

  /**
   * Calculates cost of Hiring between dates.
   *  @param id vehicle id
   * @param startDate for calculating the cost
   * @param endDate for calculating the cost
   */
  @Override
  public VehicleHiringQuote calculateCostOfHiring(Long id, LocalDate startDate, LocalDate endDate) {

    Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
    if (vehicleOptional.isPresent() ){
      Vehicle vehicle = vehicleOptional.get();
      String vehicleCategory = vehicle.getVehicleCategory();

      Optional<VehicleCategoryPrice> vehicleCategoryPriceOptional = vehicleCategoryPriceRepository
        .findById(vehicleCategory);

      if (vehicleCategoryPriceOptional.isPresent()){
        VehicleCategoryPrice vehicleCategoryPrice = vehicleCategoryPriceOptional.get();

        // this price is per day
        long noOfDays = DAYS.between(startDate, endDate);

        BigDecimal hiringPrice = vehicleCategoryPrice.getPrice()
          .multiply(BigDecimal.valueOf(noOfDays));
        return new VehicleHiringQuote(vehicle, startDate, endDate, hiringPrice);
      } else {
        String errorMessage = "Can not find pricing for the vehicle type.";
        log.debug(errorMessage);
        throw new VehicleHireServiceException(errorMessage);
      }
    } else {
      String errorMessage = String.format("Vehicle with id {%s} not found", id);
      log.error(errorMessage);
      throw new VehicleNotFoundException(errorMessage);
    }
  }
}
