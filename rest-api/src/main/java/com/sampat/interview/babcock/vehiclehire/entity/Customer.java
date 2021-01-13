package com.sampat.interview.babcock.vehiclehire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="name")
  private String name;

  @Column(name="customer_type")
  private String customerType;

  public Customer(String name, String customerType){
    this.name = name;
    this.customerType = customerType;
  }
}
