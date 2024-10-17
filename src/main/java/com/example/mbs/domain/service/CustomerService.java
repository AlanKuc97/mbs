package com.example.mbs.domain.service;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.api.model.CustomerCreateDTO;
import com.example.mbs.api.model.CustomerDTO;
import com.example.mbs.domain.mapper.CustomerDtoMapper;
import com.example.mbs.domain.mapper.persistence.CustomerMapper;
import com.example.mbs.persistence.repository.CustomerRepository;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerService {

  CustomerRepository customerRepository;
  CustomerMapper customerMapper;
  AddressService addressService;
  AccountService accountService;

  @Transactional
  public CustomerDTO createCustomer(CustomerCreateDTO dto) {
    AccountDTO accountDto = accountService.getAccount(dto.getAccountId()).orElse(null);
    Set<AddressDTO> addressDtos = addressService.getAddresses(dto.getAddressesId());
    return CustomerDtoMapper.dtoOf(
      customerRepository.save(customerMapper.entityOf(dto, accountDto, addressDtos)),
      accountDto,
      addressDtos
    );
  }

}
