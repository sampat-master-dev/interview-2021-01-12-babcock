package com.sampat.interview.babcock.vehiclehire.repository;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleHireRepositoryTest {
  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

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

    vehicleHireRepository.save(new VehicleHire(vehicle1.getId(), customer2.getId(), LocalDate
      .of(2021, 1, 1), LocalDate.of(2021, 1, 12)));
  }

  @Test
  public void whenTodayIs12Jan2021getVehiclesThatCanBeHiredTodayReturnsOneVehicle(){
    Clock clock12thJanuary = Clock.fixed(Instant.parse("2021-01-12T06:00:00Z"), ZoneOffset.systemDefault());
    List<Vehicle> vehiclesThatCanBeHiredToday = vehicleHireRepository
      .getVehiclesThatCanBeHiredToday(LocalDate.now(clock12thJanuary));

    assertNotNull(vehiclesThatCanBeHiredToday);
    assertEquals(1, vehiclesThatCanBeHiredToday.size());
    assertVehicle(vehiclesThatCanBeHiredToday.get(0), "BD12KYC", "VAN", "Toyota", "Aygo", "Petrol");

  }

  @Test
  public void whenTodayIs13Jan2021thgetVehiclesThatCanBeHiredTodayReturnsBothVehicles(){
    Clock clock11thJanuary = Clock.fixed(Instant.parse("2021-01-13T06:00:00Z"), ZoneOffset.systemDefault());
    List<Vehicle> vehiclesThatCanBeHiredToday = vehicleHireRepository
      .getVehiclesThatCanBeHiredToday(LocalDate.now(clock11thJanuary));

    assertNotNull(vehiclesThatCanBeHiredToday);
    assertEquals(2, vehiclesThatCanBeHiredToday.size());
    assertVehicle(vehiclesThatCanBeHiredToday.get(0), "BC11KYC", "SMALL", "Toyota", "Avensis", "Petrol");
    assertVehicle(vehiclesThatCanBeHiredToday.get(1), "BD12KYC", "VAN", "Toyota", "Aygo", "Petrol");

  }
}