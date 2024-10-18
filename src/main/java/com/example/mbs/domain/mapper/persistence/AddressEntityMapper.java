package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Address;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.AddressEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddressEntityMapper {

  ApplicationProperties applicationProperties;

  public Optional<AddressEntity> entityOf(Address address) {
    return Optional.ofNullable(address)
      .map(dto -> AddressEntity.builder()
        .id(dto.getId())
        .city(dto.getCity())
        .state(dto.getState())
        .street(dto.getStreet())
        .zipCode(dto.getZipCode())
        .version_num(address.getVersionNum())
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

  public List<AddressEntity> entitiesOf(List<Address> addresses) {
    return addresses.stream()
      .map(this::entityOf)
      .flatMap(Optional::stream)
      .collect(Collectors.toList());
  }

}
