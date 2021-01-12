package com.sampat.interview.babcock.vehiclehire.service;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.*;

import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import com.sampat.interview.babcock.vehiclehire.repository.CustomerRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleRepository;
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

  @BeforeEach
  public void setup() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();

    Vehicle vehicle1 = vehicleRepository
      .save(new Vehicle("BC11KYC", "SMALL", "Toyota", "Avensis", "Petrol"));
    Vehicle vehicle2 = vehicleRepository
      .save(new Vehicle("BD12KYC", "VAN", "Toyota", "Aygo", "Petrol"));

    customerRepository.save(new Customer("First Customer", "PRIVATE"));
    Customer customer2 = customerRepository.save(new Customer("Second Customer", "PRIVATE"));
    customerRepository.save(new Customer("Apple", "BUSINESS"));

    vehicleHireRepository.saveAndFlush(new VehicleHire(vehicle1.getId(), customer2.getId(), LocalDate
      .of(2021, 1, 1), LocalDate.of(2021, 1, 12)));
  }

  /**
   * Today is fixed to 12th January in VehicleHireTestConfig
   */
  @Test
  void whenTodayIs12thJanuaryFindVehiclesAvailableForHireTodayReturnsOneVehicle() {
    List<Vehicle> vehiclesThatCanBeHiredToday = vehicleHireServiceImpl.findVehiclesAvailableForHireToday();

    assertNotNull(vehiclesThatCanBeHiredToday);
    assertEquals(1, vehiclesThatCanBeHiredToday.size());
    assertVehicle(vehiclesThatCanBeHiredToday.get(0), "BD12KYC", "VAN", "Toyota", "Aygo", "Petrol");

  }
}