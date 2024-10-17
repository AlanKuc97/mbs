package com.example.mbs.api.model.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ErrorType {

  SOMETHING_WENT_WRONG("ERR001", "Something went wrong"),
  INVALID_DATA("ERR002", "Invalid input data"),
  NOT_FOUND("ERR003", "Entity was not found");

  String code;
  String reason;
}
