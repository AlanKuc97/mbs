package com.example.mbs.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.Set;

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
