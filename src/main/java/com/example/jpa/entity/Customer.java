package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customer_tb")
@SequenceGenerator(
        name = "customer_generator",
        sequenceName = "customer_seq",
        initialValue = 1,
        allocationSize = 50)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    private Long id;
    private String name;
    private Long registerDate;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
        this.registerDate = System.currentTimeMillis();
    }

    public static Customer sample() {
        return new Customer(1L, "Kim");
    }
}
