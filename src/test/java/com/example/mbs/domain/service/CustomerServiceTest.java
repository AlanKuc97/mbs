package com.example.mbs.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.mbs.api.model.Customer;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.api.model.CustomerSearch;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

  @Autowired
  CustomerService customerService;

  @Test
  @Order(1)
  void getCustomerSearch() {
    String searchTerm = "name1@example.com";
    int page = 0;
    int size = 10;
    CustomerSearch customerSearch = customerService.getCustomerSearch(searchTerm, page, size);
    assertNotNull(customerSearch);
    assertEquals(1, customerSearch.getTotalElements());
    assertEquals("123-456-0001", customerSearch.getCustomers().get(0).getPhoneNumber());
  }

  @Test
  @Order(2)
  void saveCustomer() {
    CustomerSave dto = CustomerSave.builder()
        .name("Test")
        .lastName("Tester")
        .type("PRIVATE")
        .email("test@tester.test")
        .birthDate(OffsetDateTime.now())
        .phoneNumber("+370testertest")
        .addressesId(List.of(1L, 2L))
        .accountId(1L)
        .build();
    Customer savedCustomer = customerService.saveCustomer(null, dto);
    assertNotNull(savedCustomer);
    assertNotNull(savedCustomer.getId());
    assertNotNull(savedCustomer.getAddresses());
    assertEquals(2, savedCustomer.getAccount().getNumberOfOwners());
    assertEquals(1L, savedCustomer.getAccount().getId());
  }

  @Test
  @Order(3)
  void updateCustomer() {
    CustomerSave dto = CustomerSave.builder()
        .name("Updated")
        .lastName("Tester")
        .type("PRIVATE")
        .email("test@tester.test")
        .birthDate(OffsetDateTime.now())
        .phoneNumber("+370testertest")
        .addressesId(List.of(1L, 2L))
        .accountId(1L)
        .build();
    Customer savedCustomer = customerService.saveCustomer(1L, dto);
    assertNotNull(savedCustomer);
    assertNotNull(savedCustomer.getId());
    assertNotNull(savedCustomer.getAddresses());
    assertEquals(1L, savedCustomer.getAccount().getId());
    assertEquals("Updated", savedCustomer.getName());
  }

  @Test
  @Order(4)
  void throwExceptionWhenAccountNotFound() {
    Long customerId = 1L;
    CustomerSave dto = CustomerSave.builder()
        .name("Test")
        .lastName("Tester")
        .type("PRIVATE")
        .email("test@tester.test")
        .birthDate(OffsetDateTime.now())
        .phoneNumber("+370testertest")
        .addressesId(List.of(1L, 2L))
        .build();
    assertThatThrownBy(() -> customerService.saveCustomer(customerId, dto))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Customer cannot exist without account");
  }

  @Test
  @Order(5)
  void throwExceptionWhenAddressesNotFound() {
    Long customerId = 1L;
    CustomerSave dto = CustomerSave.builder()
        .name("Test")
        .lastName("Tester")
        .type("PRIVATE")
        .email("test@tester.test")
        .birthDate(OffsetDateTime.now())
        .phoneNumber("+370testertest")
        .accountId(1L)
        .build();
    assertThatThrownBy(() -> customerService.saveCustomer(customerId, dto))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Customer cannot exist without address");
  }

}
