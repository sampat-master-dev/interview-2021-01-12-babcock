package com.sampat.interview.babcock.vehiclehire.repository;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
  List<Vehicle> findByRegistrationNumber(String registrationNumber);
}
