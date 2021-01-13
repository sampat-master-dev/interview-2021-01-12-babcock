package com.sampat.interview.babcock.vehiclehire.controller;

import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.ESTATE_CAR;
import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.SMALL_CAR;
import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.VAN;
import static com.sampat.interview.babcock.vehiclehire.utility.AssertionUtility.assertVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import com.sampat.interview.babcock.vehiclehire.model.VehicleHiringQuote;
import com.sampat.interview.babcock.vehiclehire.repository.CustomerRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleHireRepository;
import com.sampat.interview.babcock.vehiclehire.repository.VehicleRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

  private LocalDate localDate20200101 = LocalDate.of(2021, 1, 1);
  private LocalDate localDate20200112 = LocalDate.of(2021, 1, 12);
  private LocalDate startDate20210110 = LocalDate.of(2021, 1, 10);
  private LocalDate endDate20210120 = LocalDate.of(2021, 1, 20);

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

  private Vehicle vehicle1;
  private Vehicle vehicle2;
  private Vehicle vehicle3;

  @BeforeEach
  public void setup() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();

    vehicle1 = vehicleRepository
      .save(new Vehicle("BC11KYC", SMALL_CAR, "Toyota", "Avensis", "Petrol"));
    vehicle2 = vehicleRepository
      .save(new Vehicle("BD12KYC", ESTATE_CAR, "Toyota", "Corolla", "Petrol"));
    vehicle3 = vehicleRepository
      .save(new Vehicle("BE12KYC", VAN, "Toyota", "Aygo", "Petrol"));

    customerRepository.save(new Customer("First Customer", "PRIVATE"));
    Customer customer2 = customerRepository.save(new Customer("Second Customer", "PRIVATE"));
    customerRepository.save(new Customer("Apple", "BUSINESS"));

    vehicleHireRepository.save(new VehicleHire(vehicle1.getId(), customer2.getId(),
      localDate20200101, localDate20200112));
  }

  @AfterEach
  private void tearDown() {
    vehicleRepository.deleteAll();
    customerRepository.deleteAll();
    vehicleHireRepository.deleteAll();
  }

  @Test
  public void callGetVehiclesAvailableToHireToday() throws Exception {
    List<Vehicle> vehicleList = Arrays.asList(vehicle2, vehicle3);

    String expectedVehiclesResponse = objectMapper.writeValueAsString(vehicleList);
    MvcResult mvcResult = this.mockMvc.perform(get("/vehiclehire/availableToday"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json(expectedVehiclesResponse, true))
      .andReturn();

    String contentAsString = mvcResult.getResponse().getContentAsString();
    assertNotNull(contentAsString);

    List<Vehicle> vehicleListReceived = objectMapper
      .readValue(contentAsString, new TypeReference<List<Vehicle>>() {
      });
    assertNotNull(vehicleListReceived);
    assertEquals(2, vehicleListReceived.size());
    assertVehicle(vehicleListReceived.get(0), "BD12KYC", ESTATE_CAR, "Toyota", "Corolla", "Petrol");
    assertVehicle(vehicleListReceived.get(1), "BE12KYC", VAN, "Toyota", "Aygo", "Petrol");
  }

  @Test
  public void callCalculateCostVehicle1() throws Exception {
    VehicleHiringQuote expectedVehicleHiringQuote = new VehicleHiringQuote(vehicle1,
      startDate20210110, endDate20210120,
      BigDecimal.valueOf(250));

    callCalculateAndAssertValues(expectedVehicleHiringQuote, vehicle1);
  }

  @Test
  public void callCalculateCostVehicle2() throws Exception {
    VehicleHiringQuote expectedVehicleHiringQuote = new VehicleHiringQuote(vehicle2,
      startDate20210110, endDate20210120,
      BigDecimal.valueOf(350));

    callCalculateAndAssertValues(expectedVehicleHiringQuote, vehicle2);
  }

  @Test
  public void callCalculateCostVehicle3() throws Exception {
    VehicleHiringQuote expectedVehicleHiringQuote = new VehicleHiringQuote(vehicle3,
      startDate20210110, endDate20210120,
      BigDecimal.valueOf(500));

    callCalculateAndAssertValues(expectedVehicleHiringQuote, vehicle3);
  }

  private void callCalculateAndAssertValues(VehicleHiringQuote expectedVehicleHiringQuote,
    Vehicle vehicle) throws Exception {

    String expectedVehicleHiringQuoteStr = objectMapper
      .writeValueAsString(expectedVehicleHiringQuote);
    String startDateStr = objectMapper
      .writeValueAsString(expectedVehicleHiringQuote.getStartDate());
    String endDateStr = objectMapper.writeValueAsString(expectedVehicleHiringQuote.getEndDate());

    MvcResult mvcResult = this.mockMvc
      .perform(get("/vehiclehire/{id}/calculatecost/", vehicle.getId())
        .param("startDate",
          expectedVehicleHiringQuote.getStartDate().format(DateTimeFormatter.ISO_DATE))
        .param("endDate",
          expectedVehicleHiringQuote.getEndDate().format(DateTimeFormatter.ISO_DATE))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json(expectedVehicleHiringQuoteStr, true))
      .andReturn();

    String contentAsString = mvcResult.getResponse().getContentAsString();
    assertNotNull(contentAsString);

    VehicleHiringQuote vehicleHiringQuoteReceived = objectMapper
      .readValue(contentAsString, new TypeReference<VehicleHiringQuote>() {
      });
    assertNotNull(vehicleHiringQuoteReceived);
    assertEquals(expectedVehicleHiringQuote.getHiringCost(),
      vehicleHiringQuoteReceived.getHiringCost());
    Vehicle vehicleReceived = vehicleHiringQuoteReceived.getVehicle();
    assertVehicle(vehicleReceived, vehicle.getRegistrationNumber(), vehicle.getVehicleCategory(),
      vehicle.getMake(), vehicle.getModel(), vehicle.getFuelType());
  }

}