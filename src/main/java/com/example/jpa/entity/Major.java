package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

//    @OneToMany(mappedBy = "major")
//    private List<Student> students;
//
//    public Major() {
//        this.students = new ArrayList<>();
//    }
    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
