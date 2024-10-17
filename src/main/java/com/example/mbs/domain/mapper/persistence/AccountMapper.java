package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.Account;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountMapper {

  ApplicationProperties applicationProperties;

  public Account entityOf(AccountDTO accountDto) {
    return Account.builder()
      .id(accountDto.getId())
      .number(accountDto.getNumber())
      // I don't like this approach and I would use triggers
      // for the rest of the fields with normal DB like MySQL/MariaDB/MSSQL/OracleDB
      // TODO: use docker-compose and MariaDB image
      .version_num(1L)
      .creation_date(LocalDateTime.now())
      .created_by(applicationProperties.getDatabaseUsername())
      .last_modified_by(applicationProperties.getDatabaseUsername())
      .last_modified_date(LocalDateTime.now())
      .build();
  }

}
