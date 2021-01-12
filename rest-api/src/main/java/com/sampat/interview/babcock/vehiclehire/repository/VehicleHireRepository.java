package com.sampat.interview.babcock.vehiclehire.repository;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import com.sampat.interview.babcock.vehiclehire.entity.VehicleHire;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleHireRepository extends JpaRepository<VehicleHire, Long> {

  @Query(value = "SELECT v FROM Vehicle v WHERE v.id NOT IN (SELECT vh.vehicleId FROM VehicleHire vh WHERE vh.rentalEndDate >= :localDate)")
  List<Vehicle> getVehiclesThatCanBeHiredToday(@Param("localDate") LocalDate localDate);

}
