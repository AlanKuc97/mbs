package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressDTO {

    @NotNull
    Long id;
    @NotNull
    String street;
    @NotNull
    String city;
    @NotNull
    String state;
    @NotNull
    String zipCode;

}
