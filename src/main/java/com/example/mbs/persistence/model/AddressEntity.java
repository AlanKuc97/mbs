package com.example.mbs.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "ADDRESS")
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@Audited
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity extends BaseEntity {

  String street;
  String city;
  String state;
  String zipCode;
  @ManyToMany(mappedBy = "addresses")
  List<CustomerEntity> customers;

}
