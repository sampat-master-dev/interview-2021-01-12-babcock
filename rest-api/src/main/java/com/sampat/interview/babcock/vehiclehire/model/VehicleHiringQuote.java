package com.sampat.interview.babcock.vehiclehire.model;

import com.sampat.interview.babcock.vehiclehire.entity.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHiringQuote {
  private Vehicle vehicle;

  private LocalDate startDate;
  private LocalDate endDate;

  private BigDecimal hiringCost;
}
