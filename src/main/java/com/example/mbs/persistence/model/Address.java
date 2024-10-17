package com.example.mbs.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends BaseEntity {

  String street;
  String city;
  String state;
  String zipCode;
  @ManyToMany(mappedBy = "addresses")
  Set<Customer> customers;

}
