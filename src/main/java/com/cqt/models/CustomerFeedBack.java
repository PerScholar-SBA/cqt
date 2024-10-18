package com.cqt.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import com.cqt.models.Customer;
import com.cqt.models.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerFeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lookup field for Customer table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Lookup field for Product table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Double rating;
    private String queryText;


    private LocalDate openDate;

}
