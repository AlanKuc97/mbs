package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Account {

  @NotNull
  Long id;
  @NotNull
  String number;
  @NotNull
  Long numberOfOwners;
  @NotNull
  Long versionNum;

}
