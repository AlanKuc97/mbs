package com.example.mbs.api.model.error;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Error {

  @NotNull
  String code;
  @NotNull
  String reason;
  @Nullable
  String message;

}
