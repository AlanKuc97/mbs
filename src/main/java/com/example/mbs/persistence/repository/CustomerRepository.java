package com.example.mbs.persistence.repository;

import com.example.mbs.persistence.model.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  @Query(
        """
           SELECT
             c
           FROM
             CustomerEntity c
           LEFT JOIN c.addresses a
           WHERE
             LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR
             LOWER(c.last_name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR
             LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR
             LOWER(c.type) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR
             LOWER(a.street) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR
             LOWER(a.city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
        """
  )
  Page<CustomerEntity> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

}
