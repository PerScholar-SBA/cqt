package com.cqt.models;

import com.cqt.models.Customer;
import com.cqt.models.Product;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class CustomerQuery {

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

    private String queryText;
    // Add a Status field
    @Enumerated(EnumType.STRING)  // Enum stored as a String in the database
    private Status status;  // Define this enum elsewhere
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    // Set default value for openDate to the current date before persisting
    @PrePersist
    protected void onCreate() {
        this.openDate = LocalDateTime.now();
    }
}