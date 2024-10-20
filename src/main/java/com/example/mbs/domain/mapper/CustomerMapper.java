package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.Customer;
import com.example.mbs.persistence.model.CustomerEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

  public static Customer dtoOf(CustomerEntity entity) {
    return Customer.builder()
      .id(entity.getId())
      .name(entity.getName())
      .lastName(entity.getLast_name())
      .phoneNumber(entity.getPhone_number())
      .email(entity.getEmail())
      .birthDate(entity.getBirth_date())
      .type(entity.getType())
      .versionNum(entity.getVersion_num())
      .account(AccountMapper.dtoOf(entity.getAccount()))
      .addresses(AddressMapper.dtosOf(entity.getAddresses()))
      .build();
  }

  public static List<Customer> dtosOf(List<CustomerEntity> entities) {
    return entities.stream()
      .map(CustomerMapper::dtoOf)
      .toList();
  }

}
