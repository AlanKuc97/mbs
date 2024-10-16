package com.example.mbs.domain.service;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.domain.mapper.AccountDtoMapper;
import com.example.mbs.persistence.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {

    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Optional<AccountDTO> getAccount(Long id){
        return accountRepository.findById(id)
            .map(AccountDtoMapper::dtoOf);
    }

}
