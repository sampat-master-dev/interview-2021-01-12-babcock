package com.sampat.interview.babcock.vehiclehire.controller;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import com.sampat.interview.babcock.vehiclehire.repository.CustomerRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleHireControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  private Vehicle vehicle2;

  @BeforeEach
  public void setup() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();

    Vehicle vehicle1 = vehicleRepository
      .save(new Vehicle("BC11KYC", "SMALL", "Toyota", "Avensis", "Petrol"));
    vehicle2 = vehicleRepository
      .save(new Vehicle("BD12KYC", "VAN", "Toyota", "Aygo", "Petrol"));

    customerRepository.save(new Customer("First Customer", "PRIVATE"));
    Customer customer2 = customerRepository.save(new Customer("Second Customer", "PRIVATE"));
    customerRepository.save(new Customer("Apple", "BUSINESS"));

    vehicleHireRepository.save(new VehicleHire(vehicle1.getId(), customer2.getId(), LocalDate
      .of(2021, 1, 1), LocalDate.of(2021, 1, 12)));
  }

  @AfterEach
  private void tearDown() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();
  }

  @Test
  public void getVehiclesAvailableToHireToday() throws Exception {
    List<Vehicle> vehicleList = Collections.singletonList(vehicle2);

    String expectedVehiclesResponse = objectMapper.writeValueAsString(vehicleList);
    MvcResult mvcResult = this.mockMvc.perform(get("/vehiclehire/availableToday"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json(expectedVehiclesResponse, true))
      .andReturn();

    String contentAsString = mvcResult.getResponse().getContentAsString();
    assertNotNull(contentAsString);

    List<Vehicle> vehicleListReceived = objectMapper.readValue(contentAsString, new TypeReference<List<Vehicle>>() {});
    assertNotNull(vehicleListReceived);
    assertEquals(1, vehicleListReceived.size());
    assertVehicle(vehicleListReceived.get(0), "BD12KYC", "VAN", "Toyota", "Aygo", "Petrol");
  }

}