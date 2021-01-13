package com.sampat.interview.babcock.vehiclehire.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "vehicle_hire")
public class VehicleHire {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="vehicle_id")
  private long vehicleId;

  @Column(name="customer_id")
  private long customerId;

  @Column(name="rental_start_date")
  private LocalDate rentalStartDate;

  @Column(name="rental_end_date")
  private LocalDate rentalEndDate;

  public VehicleHire(long vehicleId, long customerId, LocalDate rentalStartDate, LocalDate rentalEndDate){
    this.vehicleId = vehicleId;
    this.customerId = customerId;
    this.rentalStartDate = rentalStartDate;
    this.rentalEndDate = rentalEndDate;
  }
}