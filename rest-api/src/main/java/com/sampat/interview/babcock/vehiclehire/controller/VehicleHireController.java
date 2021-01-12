package com.sampat.interview.babcock.vehiclehire.controller;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.model.VehicleHiringQuote;
import com.sampat.interview.babcock.vehiclehire.service.VehicleHireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vehiclehire")
@Api(value = "VehicleHireController", description = "Vehicle Hire Rest API.")
public class VehicleHireController {

  private VehicleHireService vehicleHireService;

  @Autowired
  public VehicleHireController(VehicleHireService vehicleHireService) {
    this.vehicleHireService = vehicleHireService;
  }

  @ApiOperation(value = "Gets all Vehicles available for Hire Today.",
    response = List.class, tags = "1.0.0")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success|OK"),
    @ApiResponse(code = 401, message = "not authorized!"),
    @ApiResponse(code = 403, message = "forbidden!!!"),
    @ApiResponse(code = 404, message = "not found!!!")})
  @GetMapping(path = "/availableToday", produces = "application/json")
  public List<Vehicle> getVehiclesAvailableToHireToday() {
    return vehicleHireService.findVehiclesAvailableForHireToday();
  }

  @ApiOperation(value = "Calculate Cost Of Hiring for the date range.",
    response = VehicleHiringQuote.class, tags = "1.0.0")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success|OK - Successfully returns Cost of Hiring."),
    @ApiResponse(code = 404, message = "not found!!!")})
  @GetMapping(path = "/{id}/calculatecost/", produces = "application/json")
  public VehicleHiringQuote calculateCostOfHiring(@PathVariable("id") Long id,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

    return vehicleHireService.calculateCostOfHiring(id, startDate, endDate);
  }
}
