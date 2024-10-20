package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Account;
import com.example.mbs.api.model.Address;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.persistence.model.CustomerEntity;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntityMapper {

  public static Optional<CustomerEntity> entityOf(
      Long id,
      Long versionNum,
      CustomerSave customerSave,
      Account account,
      List<Address> addresses
  ) {
    return Optional.ofNullable(customerSave)
      .map(dto -> CustomerEntity.builder()
        .id(id)
        .name(dto.getName())
        .last_name(dto.getLastName())
        .email(dto.getEmail())
        .type(dto.getType())
        .birth_date(dto.getBirthDate())
        .phone_number(dto.getPhoneNumber())
        .account(
          AccountEntityMapper.entityOf(account)
            .orElseThrow(() -> new IllegalArgumentException("Error during entity mapping"))
        )
        .addresses(AddressEntityMapper.entitiesOf(addresses))
        .version_num(versionNum)
        .build()
      );
  }

}
