package com.example.mbs.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationProperties {

  @NotNull
  @Value("${spring.datasource.username}")
  String databaseUsername;

}
