package com.cqt.repository;

import com.cqt.models.Customer;
import com.cqt.models.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository< Query, Long> {
}
