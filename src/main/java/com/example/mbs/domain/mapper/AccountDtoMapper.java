package com.example.mbs.domain.mapper;

import com.example.mbs.api.model.AccountDTO;
import com.example.mbs.persistence.model.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDtoMapper {

  public static AccountDTO dtoOf(Account entity) {
    return AccountDTO.builder()
      .id(entity.getId())
      .number(entity.getNumber())
      .numberOfOwners(entity.getNumberOfOwners())
      .build();
  }

}
