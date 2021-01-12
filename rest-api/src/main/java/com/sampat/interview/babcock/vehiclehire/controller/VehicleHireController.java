package com.sampat.interview.babcock.vehiclehire.controller;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.service.VehicleHireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vehiclehire")
@Tag(name="vehicle Hire", description = "Vehicle Hire Rest API.")
public class VehicleHireController {

  private VehicleHireService vehicleHireService;

  @Autowired
  public VehicleHireController(VehicleHireService vehicleHireService) {
    this.vehicleHireService = vehicleHireService;
  }

  @Operation(summary = "Gets all Vehicles available for Hire Today.",
              description = "Returns all the vehicles that are available for Hire today.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully returns all the vehicles that are available for Hire today.",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vehicle.class)))),
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  @GetMapping(path = "/availableToday", produces = "application/json")
  public List<Vehicle> getVehiclesAvailableToHireToday() {
    return vehicleHireService.findVehiclesAvailableForHireToday();
  }
}
