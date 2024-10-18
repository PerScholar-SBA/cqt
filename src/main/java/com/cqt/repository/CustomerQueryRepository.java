package com.cqt.repository;

import com.cqt.models.Customer;
import com.cqt.models.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerQueryRepository extends JpaRepository<CustomerQuery, Long> {
}
