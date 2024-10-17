package com.example.mbs.domain.service;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.api.model.CustomerCreateDTO;
import com.example.mbs.api.model.CustomerDTO;
import com.example.mbs.domain.mapper.CustomerDtoMapper;
import com.example.mbs.domain.mapper.persistence.CustomerMapper;
import com.example.mbs.persistence.model.Customer;
import com.example.mbs.persistence.repository.CustomerRepository;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CustomerService {

  CustomerRepository customerRepository;
  CustomerMapper customerMapper;
  AddressService addressService;
  AccountService accountService;

  @Transactional(propagation = Propagation.SUPPORTS)
  public Optional<CustomerDTO> createCustomer(CustomerCreateDTO dto) {
    try {
      AccountDTO accountDto = accountService.getAccount(dto.getAccountId()).orElse(null);
      Set<AddressDTO> addressDtos = addressService.getAddresses(dto.getAddressesId());
      Customer customer = customerRepository.saveAndFlush(
          customerMapper.entityOf(dto, accountDto, addressDtos)
      );
      return Optional.of(CustomerDtoMapper.dtoOf(
        customer,
        accountDto,
        addressDtos
      ));
    } catch (DataIntegrityViolationException e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }

}
