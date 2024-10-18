package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.Account;
import com.example.mbs.persistence.model.AccountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

  public static Account dtoOf(AccountEntity entity) {
    return Account.builder()
      .id(entity.getId())
      .number(entity.getNumber())
      .numberOfOwners(entity.getNumberOfOwners())
      .versionNum(entity.getVersion_num())
      .build();
  }

}
