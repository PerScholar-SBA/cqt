package com.cqt.repository;

import com.cqt.models.Customer;
import com.cqt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
