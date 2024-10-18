package com.example.mbs.domain.service;

import com.example.mbs.api.model.Account;
import com.example.mbs.domain.mapper.AccountMapper;
import com.example.mbs.persistence.repository.AccountRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {

  AccountRepository accountRepository;

  @Transactional(readOnly = true)
  public Optional<Account> getAccount(Long id) {
    return accountRepository.findById(id)
        .map(AccountMapper::dtoOf);
  }

}
