package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.api.model.AddressDTO;
import com.example.mbs.api.model.CustomerDTO;
import com.example.mbs.persistence.model.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDtoMapper {

    public static CustomerDTO dtoOf(
        Customer entity,
        AccountDTO accountDto,
        Set<AddressDTO> addressDTOS
    ) {
        return CustomerDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .lastName(entity.getLast_name())
            .phoneNumber(entity.getPhone_number())
            .email(entity.getEmail())
            .birthDate(entity.getBirth_date())
            .type(entity.getType())
            .account(accountDto)
            .addresses(addressDTOS)
            .build();
    }


}
