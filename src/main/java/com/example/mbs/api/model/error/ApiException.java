package com.example.mbs.api.model.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class ApiException extends RuntimeException {

  HttpStatus httpStatus;
  ErrorType errorType;

  public ApiException(String message, HttpStatus httpStatus, ErrorType errorType) {
    super(message);
    this.httpStatus = httpStatus;
    this.errorType = errorType;
  }
}
