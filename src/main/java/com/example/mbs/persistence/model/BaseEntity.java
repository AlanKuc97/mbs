package com.example.mbs.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@SuppressWarnings("checkstyle:MemberName")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Version
  Long version_num;
  @Column(updatable = false)
  String created_by;
  @Column(updatable = false)
  LocalDateTime creation_date;
  String last_modified_by;
  LocalDateTime last_modified_date;

  @PrePersist
  protected void onCreate() {
    this.created_by = "FUTURE_APPLICATION_USER_ID"; // Replace with actual user context
    this.creation_date = LocalDateTime.now();
    this.last_modified_by = this.created_by;
    this.last_modified_date = this.creation_date;
  }

  @PreUpdate
  protected void onUpdate() {
    this.last_modified_by = "FUTURE_APPLICATION_USER_ID"; // Replace with actual user context
    this.last_modified_date = LocalDateTime.now();
  }

}
