package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.Address;
import com.example.mbs.persistence.model.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddressMapper {

    ApplicationProperties applicationProperties;

    public Address entityOf(AddressDTO addressDto){
        return Address.builder()
            .id(addressDto.getId())
            .city(addressDto.getCity())
            .state(addressDto.getState())
            .street(addressDto.getStreet())
            .zipCode(addressDto.getZipCode())
            // I don't like this approach and I would use triggers
            // for the rest of the fields with normal DB like MySQL/MariaDB/MSSQL/OracleDB
            // TODO: use docker-compose and MariaDB image, after that make mapper static
            .version_num(1L)
            .creation_date(LocalDateTime.now())
            .created_by(applicationProperties.getDatabaseUsername())
            .last_modified_by(applicationProperties.getDatabaseUsername())
            .last_modified_date(LocalDateTime.now())
            .build();
    }

    public Set<Address> entitiesOf(
        Set<AddressDTO> addressDtos
    ){
        return addressDtos.stream()
            .map(this::entityOf)
            .collect(Collectors.toSet());
    }

}
