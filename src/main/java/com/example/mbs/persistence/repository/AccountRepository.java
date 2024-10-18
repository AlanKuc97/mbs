package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
