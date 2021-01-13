package com.sampat.interview.babcock.vehiclehire.service;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.*;

import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleCategoryPrice;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import com.sampat.interview.babcock.vehiclehire.model.VehicleHiringQuote;
import com.sampat.interview.babcock.vehiclehire.repository.CustomerRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleCategoryPriceRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleRepository;
import com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleHireServiceImplTest {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  @Autowired
  private VehicleHireServiceImpl vehicleHireServiceImpl;

  @Autowired
  private VehicleCategoryPriceRepository vehicleCategoryPriceRepository;

  private Vehicle vehicle1;
  private Vehicle vehicle2;
  private Vehicle vehicle3;

  @BeforeEach
  public void setup() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();

    vehicle1 = vehicleRepository
      .save(new Vehicle("BC11KYC", AssertionUtility.SMALL_CAR, "Toyota", "Avensis", "Petrol"));
    vehicle2 = vehicleRepository
      .save(new Vehicle("BD12KYC", AssertionUtility.ESTATE_CAR, "Toyota", "Corolla", "Petrol"));

    vehicle3 = vehicleRepository
      .save(new Vehicle("BE12KYC", AssertionUtility.VAN, "Toyota", "Aygo", "Petrol"));

    customerRepository.save(new Customer("First Customer", "PRIVATE"));
    Customer customer2 = customerRepository.save(new Customer("Second Customer", "PRIVATE"));
    customerRepository.save(new Customer("Apple", "BUSINESS"));

    vehicleCategoryPriceRepository.save(new VehicleCategoryPrice(AssertionUtility.SMALL_CAR, BigDecimal.valueOf(25)));
    vehicleCategoryPriceRepository.save(new VehicleCategoryPrice(AssertionUtility.ESTATE_CAR, BigDecimal.valueOf(35)));
    vehicleCategoryPriceRepository.save(new VehicleCategoryPrice(AssertionUtility.VAN, BigDecimal.valueOf(50)));

    vehicleHireRepository.saveAndFlush(new VehicleHire(vehicle1.getId(), customer2.getId(), LocalDate
      .of(2021, 1, 1), LocalDate.of(2021, 1, 12)));
  }

  /**
   * Today is fixed to 12th January in VehicleHireTestConfig
   */
  @Test
  public void whenTodayIs12thJanuaryFindVehiclesAvailableForHireTodayReturnsOneVehicle() {
    List<Vehicle> vehiclesThatCanBeHiredToday = vehicleHireServiceImpl.findVehiclesAvailableForHireToday();

    assertNotNull(vehiclesThatCanBeHiredToday);
    assertEquals(2, vehiclesThatCanBeHiredToday.size());
    assertVehicle(vehiclesThatCanBeHiredToday.get(0), "BD12KYC", AssertionUtility.ESTATE_CAR, "Toyota", "Corolla", "Petrol");
    assertVehicle(vehiclesThatCanBeHiredToday.get(1), "BE12KYC", AssertionUtility.VAN, "Toyota", "Aygo", "Petrol");
  }

  @Test
  public void testCalculateCost() {
    LocalDate localDate20210110 = LocalDate.of(2021, 1, 10);
    LocalDate localDate20210120 = LocalDate.of(2021, 1, 20);

    VehicleHiringQuote vehicle1HiringQuote = vehicleHireServiceImpl
      .calculateCostOfHiring(vehicle1.getId(), localDate20210110, localDate20210120);

    VehicleHiringQuote vehicle2HiringQuote = vehicleHireServiceImpl
      .calculateCostOfHiring(vehicle2.getId(), localDate20210110, localDate20210120);

    VehicleHiringQuote vehicle3HiringQuote = vehicleHireServiceImpl
      .calculateCostOfHiring(vehicle3.getId(), localDate20210110, localDate20210120);

    assertEquals(BigDecimal.valueOf(250), vehicle1HiringQuote.getHiringCost());
    assertEquals(BigDecimal.valueOf(350), vehicle2HiringQuote.getHiringCost());
    assertEquals(BigDecimal.valueOf(500), vehicle3HiringQuote.getHiringCost());
  }

}