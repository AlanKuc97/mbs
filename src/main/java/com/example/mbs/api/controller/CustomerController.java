package com.example.mbs.api.controller;

import com.example.mbs.api.model.Customer;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.api.model.CustomerSearch;
import com.example.mbs.api.model.error.InvalidDataApiException;
import com.example.mbs.domain.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("PMD.PreserveStackTrace")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(
    value = "/api/customers",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {

  CustomerService customerService;

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody CustomerSave dto) {
    try {
      return ResponseEntity.ok(customerService.saveCustomer(null, dto));
    } catch (DataIntegrityViolationException | UnexpectedRollbackException e) {
      log.error(e.getMessage());
      throw new InvalidDataApiException("Invalid create customer data provided");
    }
  }

  // I will not allow updating addresses inside customer update endpoint because same address
  // can be associated with many other customers.
  // If address update is necessary,
  // I suggest on creating separate endpoint specifically for addresses
  @PatchMapping(path = "/{id}")
  public ResponseEntity<Customer> updateCustomer(
      @PathVariable Long id,
      @RequestBody CustomerSave dto
  ) {
    try {
      return ResponseEntity.ok(customerService.saveCustomer(id, dto));
    } catch (DataIntegrityViolationException | UnexpectedRollbackException e) {
      log.error(e.getMessage());
      throw new InvalidDataApiException("Invalid update customer data provided");
    }
  }

  @GetMapping("/search")
  public ResponseEntity<CustomerSearch> searchCustomers(
      @RequestParam String searchTerm,
      @RequestParam int page,
      @RequestParam int size
  ) {
    return ResponseEntity.ok(customerService.getCustomerSearch(searchTerm, page, size));
  }

}
