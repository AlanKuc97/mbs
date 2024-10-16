package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountDTO {

    @NotNull
    Long id;
    @NotNull
    String number;
    @NotNull
    Long numberOfOwners;

}
