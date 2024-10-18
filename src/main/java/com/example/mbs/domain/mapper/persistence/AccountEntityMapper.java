package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Account;
import com.example.mbs.configuration.ApplicationProperties;
import com.example.mbs.persistence.model.AccountEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountEntityMapper {

  ApplicationProperties applicationProperties;

  public Optional<AccountEntity> entityOf(Account account) {
    return Optional.ofNullable(account)
      .map(dto -> AccountEntity.builder()
        .id(dto.getId())
        .number(dto.getNumber())
        .version_num(dto.getVersionNum())
        // I don't like this approach and I would use triggers
        // for the rest of the fields with normal DB like MySQL/MariaDB/MSSQL/OracleDB
        // TODO: use docker-compose, MariaDB image and triggers. After that make mapper static
        //  and remove fields below
        .created_by(applicationProperties.getDatabaseUsername())
        .creation_date(LocalDateTime.now())
        .last_modified_by(applicationProperties.getDatabaseUsername())
        .last_modified_date(LocalDateTime.now())
        .build()
      );
  }

}
