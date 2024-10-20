package com.example.mbs.api.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerSearch {

  @NotNull
  private List<Customer> customers;
  @NotNull
  private Integer totalElements;

}
