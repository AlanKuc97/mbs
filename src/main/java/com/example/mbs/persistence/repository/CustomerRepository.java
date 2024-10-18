package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
