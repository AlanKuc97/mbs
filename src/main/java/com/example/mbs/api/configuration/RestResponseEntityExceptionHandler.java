package com.example.mbs.api.configuration;

import com.example.mbs.api.model.error.ApiException;
import com.example.mbs.api.model.error.Error;
import com.example.mbs.api.model.error.ErrorType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    return handleException(
      ErrorType.SOMETHING_WENT_WRONG,
      ex,
      HttpStatus.INTERNAL_SERVER_ERROR,
      null
    );
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleApiException(ApiException ex) {
    return handleException(ex.getErrorType(), ex, ex.getHttpStatus(), ex.getMessage());
  }

  @NotNull
  private ResponseEntity<Object> handleException(
      @NotNull ErrorType errorType,
      @NotNull Exception ex,
      @NotNull HttpStatus status,
      @Nullable String message
  ) {
    log.error(errorType.getReason(), ex);
    return new ResponseEntity<>(
        Error.builder()
          .code(errorType.getCode())
          .reason(errorType.getReason())
          .message(message)
          .build(),
        new HttpHeaders(),
        status
    );
  }



}
