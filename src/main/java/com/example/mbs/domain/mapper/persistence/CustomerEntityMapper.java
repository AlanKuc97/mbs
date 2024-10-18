package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Account;
import com.example.mbs.api.model.Address;
import com.example.mbs.api.model.CustomerSave;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.CustomerEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@SuppressWarnings("checkstyle:MissingJavadocType")
@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerEntityMapper {

  ApplicationProperties applicationProperties;
  AddressEntityMapper addressEntityMapper;
  AccountEntityMapper accountEntityMapper;

  public Optional<CustomerEntity> entityOf(
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
        .accountEntity(
          accountEntityMapper.entityOf(account)
            .orElseThrow(() -> new IllegalArgumentException("Error during entity mapping"))
        )
        .addressEntities(addressEntityMapper.entitiesOf(addresses))
        .version_num(versionNum)
        // I don't like this approach and I would use triggers
        // for the rest of the fields with normal DB like MySQL/MariaDB/MSSQL/OracleDB
        // TODO: use docker-compose, MariaDB image and triggers. After that make mapper static
        //  and remove fields below
        .created_by(applicationProperties.getDatabaseUsername())
        .creation_date(LocalDateTime.now())
        .last_modified_by(applicationProperties.getDatabaseUsername())
        .last_modified_date(LocalDateTime.now())
        .build()
      );
  }

}
