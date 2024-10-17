package com.example.mbs.api.model.error;

import org.springframework.http.HttpStatus;

public class InvalidDataApiException extends ApiException {

  public InvalidDataApiException(String message) {
    super(message, HttpStatus.BAD_REQUEST, ErrorType.INVALID_DATA);
  }

}
