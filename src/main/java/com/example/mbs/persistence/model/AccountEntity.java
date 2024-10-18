package com.example.mbs.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "Account")
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@SuperBuilder
@Audited
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountEntity extends BaseEntity {

  String number;
  @OneToMany(
      mappedBy = "accountEntity",
      fetch = FetchType.EAGER
  )
  List<CustomerEntity> customerEntities;

  //TODO: debug this if there will be time left
  public Long getNumberOfOwners() {
    return Long.valueOf(
      Optional.ofNullable(customerEntities)
      .map(List::size)
      .orElse(0)
    );
  }

}
