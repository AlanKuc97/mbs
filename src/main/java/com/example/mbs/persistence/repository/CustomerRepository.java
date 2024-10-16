package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
