package com.sampat.interview.babcock.vehiclehire.repository;

import com.sampat.interview.babcock.vehiclehire.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
