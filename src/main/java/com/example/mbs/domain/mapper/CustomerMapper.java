package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.Account;
import com.example.mbs.api.model.Address;
import com.example.mbs.api.model.Customer;
import com.example.mbs.persistence.model.CustomerEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

  public static Customer dtoOf(
      CustomerEntity entity,
      Account account,
      List<Address> addresses
  ) {
    return Customer.builder()
      .id(entity.getId())
      .name(entity.getName())
      .lastName(entity.getLast_name())
      .phoneNumber(entity.getPhone_number())
      .email(entity.getEmail())
      .birthDate(entity.getBirth_date())
      .type(entity.getType())
      .versionNum(entity.getVersion_num())
      .account(account)
      .addresses(addresses)
      .build();
  }

}
