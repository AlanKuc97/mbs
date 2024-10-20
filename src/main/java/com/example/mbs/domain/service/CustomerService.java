package com.example.mbs.domain.service;

import com.example.mbs.api.model.Account;
import com.example.mbs.api.model.Address;
import com.example.mbs.api.model.Customer;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.api.model.CustomerSearch;
import com.example.mbs.domain.mapper.CustomerMapper;
import com.example.mbs.domain.mapper.persistence.CustomerEntityMapper;
import com.example.mbs.persistence.model.CustomerEntity;
import com.example.mbs.persistence.repository.CustomerRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CustomerService {

  CustomerRepository customerRepository;
  AddressService addressService;
  AccountService accountService;

  @Transactional
  public Customer saveCustomer(@Nullable Long id, @NotNull CustomerSave dto) {
    Account account = Optional.ofNullable(dto.getAccountId())
        .flatMap(accountService::getAccount)
        .orElseThrow(() -> new IllegalArgumentException("Customer cannot exist without account"));

    List<Address> addresses = Optional.ofNullable(dto.getAddressesId())
        .map(addressService::getAddresses)
        .orElseThrow(() -> new IllegalArgumentException("Customer cannot exist without address"));

    return CustomerMapper.dtoOf(
      customerRepository.saveAndFlush(entityOf(id, dto, account, addresses))
    );
  }

  @Transactional(readOnly = true)
  public CustomerSearch getCustomerSearch(String searchTerm, int page, int size) {
    Page<CustomerEntity> customerPage =
        customerRepository.findBySearchTerm(searchTerm, PageRequest.of(page, size));
    return CustomerSearch.builder()
      .customers(CustomerMapper.dtosOf(customerPage.getContent()))
      .totalElements(customerPage.getContent().size())
      .build();
  }

  @Transactional(readOnly = true)
  public Optional<Customer> getCustomer(Long id) {
    return customerRepository.findById(id)
      .map(CustomerMapper::dtoOf);
  }

  private CustomerEntity entityOf(
      Long id,
      CustomerSave dto,
      Account account,
      List<Address> addresses
  ) {
    return CustomerEntityMapper.entityOf(
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

}
