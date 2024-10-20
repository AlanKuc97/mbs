package com.example.mbs.domain.mapper.persistence;

import com.example.mbs.api.model.Account;
import com.example.mbs.persistence.model.AccountEntity;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountEntityMapper {

  public static Optional<AccountEntity> entityOf(Account account) {
    return Optional.ofNullable(account)
      .map(dto -> AccountEntity.builder()
        .id(dto.getId())
        .number(dto.getNumber())
        .version_num(dto.getVersionNum())
        .build()
      );
  }

}
