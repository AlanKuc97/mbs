package com.example.mbs.domain.service;

import com.example.mbs.api.model.Account;
import com.example.mbs.api.model.Address;
import com.example.mbs.api.model.Customer;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.domain.mapper.CustomerMapper;
import com.example.mbs.domain.mapper.persistence.CustomerEntityMapper;
import com.example.mbs.persistence.model.BaseEntity;
import com.example.mbs.persistence.model.CustomerEntity;
import com.example.mbs.persistence.repository.CustomerRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
  CustomerEntityMapper customerEntityMapper;
  AddressService addressService;
  AccountService accountService;

  //TODO: think about propagation
  @Transactional(propagation = Propagation.NEVER)
  public Optional<Customer> saveCustomer(@Nullable Long id, @NotNull CustomerSave dto) {
    try {
      Account account = Optional.ofNullable(dto.getAccountId())
          .flatMap(accountService::getAccount)
          .orElseThrow(() -> new IllegalArgumentException("Customer cannot exist without account"));

      List<Address> addresses = Optional.ofNullable(dto.getAddressesId())
          .map(addressService::getAddresses)
          .orElseThrow(() -> new IllegalArgumentException("Customer cannot exist without address"));

      return Optional.of(
        dtoOf(customerRepository.saveAndFlush(entityOf(id, dto, account, addresses)))
      );
    } catch (DataIntegrityViolationException e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }

  @Transactional(readOnly = true)
  public Optional<Customer> getCustomer(Long id) {
    return customerRepository.findById(id)
      .map(this::dtoOf);
  }

  private CustomerEntity entityOf(
      Long id,
      CustomerSave dto,
      Account account,
      List<Address> addresses
  ) {
    return customerEntityMapper.entityOf(
        id,
        Optional.ofNullable(id)
          .flatMap(customerId -> getCustomer(customerId).map(Customer::getVersionNum))
          .orElse(0L),
        dto,
        account,
        addresses
      )
      .orElseThrow(() -> new IllegalArgumentException("Customer data is necessary"));
  }

  private Customer dtoOf(CustomerEntity entity) {
    return CustomerMapper.dtoOf(
      entity,
      accountService.getAccount(entity.getAccountEntity().getId())
        .orElseThrow(() -> new EntityNotFoundException(
          String.format(
            "Account not found with id %s",
            entity.getAccountEntity().getId()
          )
        )),
      addressService.getAddresses(
        entity.getAddressEntities().stream()
          .map(BaseEntity::getId)
          .collect(Collectors.toList())
      )
    );
  }

}
