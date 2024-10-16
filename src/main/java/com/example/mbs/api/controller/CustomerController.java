package com.example.mbs.api.controller;

import com.example.mbs.api.model.CustomerDTO;
import com.example.mbs.api.model.CustomerCreateDTO;
import com.example.mbs.domain.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreateDTO dto) {
        return ResponseEntity.ok(customerService.createCustomer(dto));
    }

}
