package com.example.mbs.api.controller;

import com.example.mbs.api.model.Customer;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.api.model.error.InvalidDataApiException;
import com.example.mbs.domain.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    return customerService.saveCustomer(null, dto)
      .map(ResponseEntity::ok)
      .orElseThrow(() -> new InvalidDataApiException("Invalid create customer data provided"));
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
    return customerService.saveCustomer(id, dto)
      .map(ResponseEntity::ok)
      .orElseThrow(() -> new InvalidDataApiException("Invalid update customer data provided"));
  }

}
