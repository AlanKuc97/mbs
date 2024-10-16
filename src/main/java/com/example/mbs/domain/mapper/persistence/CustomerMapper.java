package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.api.model.CustomerCreateDTO;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.Address;
import com.example.mbs.persistence.model.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerMapper {

    ApplicationProperties applicationProperties;
    AddressMapper addressMapper;
    AccountMapper accountMapper;

    public Customer entityOf(
        CustomerCreateDTO dto,
        AccountDTO accountDto,
        Set<AddressDTO> addressDtos
    ){
        return Customer.builder()
            .name(dto.getName())
            .last_name(dto.getLastName())
            .email(dto.getEmail())
            .type(dto.getType())
            .birth_date(dto.getBirthDate())
            .phone_number(dto.getPhoneNumber())
            .account(accountMapper.entityOf(accountDto))
            .addresses(addressMapper.entitiesOf(addressDtos))
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

}
