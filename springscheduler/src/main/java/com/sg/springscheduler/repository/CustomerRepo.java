package com.sg.springscheduler.repository;

import com.sg.springscheduler.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, String> {
}
