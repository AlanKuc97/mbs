package com.example.mbs.persistence.model;

import com.example.mbs.api.model.CustomerType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.OffsetDateTime;
import java.util.Set;

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
