package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.Address;
import com.example.mbs.persistence.model.AddressEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

  public static Address dtoOf(AddressEntity entity) {
    return Address.builder()
      .id(entity.getId())
      .city(entity.getCity())
      .state(entity.getState())
      .street(entity.getStreet())
      .zipCode(entity.getZipCode())
      .versionNum(entity.getVersion_num())
      .build();
  }

  public static List<Address> dtosOf(List<AddressEntity> entities) {
    return entities.stream()
      .map(AddressMapper::dtoOf)
      .toList();
  }

}
