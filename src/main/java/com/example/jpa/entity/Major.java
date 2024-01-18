package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MAJOR_TB")
public class Major {

    @Id
    @GeneratedValue
    private Long majorId;
    private String name;
    private String category;

    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
