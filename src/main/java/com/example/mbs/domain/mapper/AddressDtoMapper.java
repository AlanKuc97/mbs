package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.persistence.model.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDtoMapper {

    public static AddressDTO dtoOf(Address entity) {
        return AddressDTO.builder()
            .id(entity.getId())
            .city(entity.getCity())
            .state(entity.getState())
            .street(entity.getStreet())
            .zipCode(entity.getZipCode())
            .build();
    }

}
