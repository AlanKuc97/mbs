package com.example.mbs.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.Optional;
import java.util.Set;

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
