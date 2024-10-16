package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
