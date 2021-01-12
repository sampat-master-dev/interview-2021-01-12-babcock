package com.sampat.interview.babcock.vehiclehire.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_category_price")
public class VehicleCategoryPrice {
  @Id
  @Column(name="category")
  private String category;

  @Column(name="price")
  private BigDecimal price;
}
