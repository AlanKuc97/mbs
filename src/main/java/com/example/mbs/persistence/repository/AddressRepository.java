package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
