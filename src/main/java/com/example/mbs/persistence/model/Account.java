package com.example.mbs.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@Audited
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Account extends BaseEntity {

  String number;
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<Customer> customers;

  public Long getNumberOfOwners() {
    return Long.valueOf(
      Optional.ofNullable(customers)
      .map(Set::size)
      .orElse(0)
    );
  }

}
