package com.sampat.interview.babcock.vehiclehire.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRepositoryTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void testValidateCustomerSave() {
    Customer customerSaved = customerRepository.save(new Customer("First Customer", "PRIVATE"));
    Optional<Customer> customer = customerRepository.findById(customerSaved.getId());

    assertNotNull(customer);
    assertTrue(customer.isPresent());
    Customer customerFound = customer.get();

    assertEquals(customerSaved.getId(), customerFound.getId());
    assertEquals("First Customer", customerFound.getName());
    assertEquals("PRIVATE", customerFound.getCustomerType());
  }
}