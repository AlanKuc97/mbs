package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerSave {

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
  String type;
  @NotNull
  List<Long> addressesId;
  @NotNull
  Long accountId;

}
