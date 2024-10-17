package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
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
