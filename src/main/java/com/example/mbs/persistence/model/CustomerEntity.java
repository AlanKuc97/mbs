package com.example.mbs.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@SuppressWarnings("checkstyle:MemberName")
@Entity
@DynamicUpdate
@Table(name = "CUSTOMER")
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@Audited
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerEntity extends BaseEntity {

  String name;
  String last_name;
  String phone_number;
  String email;
  OffsetDateTime birth_date;
  String type;
  @ManyToMany
  @JoinTable(
      name = "address_customer",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id")
  )
  List<AddressEntity> addresses;
  @ManyToOne
  @JoinColumn(name = "account_id")
  AccountEntity account;

}
