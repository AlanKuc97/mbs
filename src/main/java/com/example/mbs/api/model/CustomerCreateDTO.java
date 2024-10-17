package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerCreateDTO {

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
  Set<Long> addressesId;
  @NotNull
  Long accountId;

}
