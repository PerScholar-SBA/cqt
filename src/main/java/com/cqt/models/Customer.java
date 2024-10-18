package com.cqt.models;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String description;
    private int rating;


}