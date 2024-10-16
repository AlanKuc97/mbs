package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerDTO {

    @NotNull
    Long id;
    @NotNull
    String name;
    @NotNull
    String lastName;
    @NotNull
    String phoneNumber;
    @NotNull
    String email;
    @NotNull
    OffsetDateTime birthDate;
    @NotNull
    CustomerType type;
    @NotNull
    Set<AddressDTO> addresses;
    @NotNull
    AccountDTO account;

}
