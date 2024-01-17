package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotel_info")
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private String id;
    private String name;
    @Column(name = "`year`")
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;

    @Embedded
    private Address address;

    public Hotel(String id, String name, int year, Address address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.address = address;
    }
}
