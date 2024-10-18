package com.cqt.repository;

import com.cqt.models.CustomerQueryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerQueryTransactionRepository extends JpaRepository<CustomerQueryTransaction, Long> {
    // You can add custom queries if needed
}