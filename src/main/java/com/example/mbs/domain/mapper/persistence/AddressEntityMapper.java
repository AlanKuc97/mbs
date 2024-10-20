package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Address;
import com.example.mbs.persistence.model.AddressEntity;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressEntityMapper {

  public static Optional<AddressEntity> entityOf(Address address) {
    return Optional.ofNullable(address)
      .map(dto -> AddressEntity.builder()
        .id(dto.getId())
        .city(dto.getCity())
        .state(dto.getState())
        .street(dto.getStreet())
        .zipCode(dto.getZipCode())
        .version_num(dto.getVersionNum())
        .build()
      );
  }

  public static List<AddressEntity> entitiesOf(List<Address> addresses) {
    return addresses.stream()
      .map(AddressEntityMapper::entityOf)
      .flatMap(Optional::stream)
      .toList();
  }

}
