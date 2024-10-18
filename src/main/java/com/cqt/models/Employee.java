package com.cqt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Name is required")
    private String name;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)  // String
    private Department department;

    @Enumerated(EnumType.STRING)  // Store role as a string in the database
    private Role role;
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull
    @NotBlank(message = "email is required")
    @Size(min = 3, max = 50, message = "email must be between 3 and 50 characters")
    private String email;
    private Double rating;
    private String description;

}