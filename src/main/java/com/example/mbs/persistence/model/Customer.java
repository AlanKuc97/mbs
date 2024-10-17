package com.example.mbs.persistence.model;

import com.example.mbs.api.model.CustomerType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@SuppressWarnings("checkstyle:MemberName")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@Audited
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Customer extends BaseEntity {

  String name;

  String last_name;
  String phone_number;
  String email;
  OffsetDateTime birth_date;
  @Enumerated(EnumType.STRING)
  CustomerType type;
  @ManyToMany
  @JoinTable(
      name = "address_customer",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id")
  )
  Set<Address> addresses;
  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;

}
