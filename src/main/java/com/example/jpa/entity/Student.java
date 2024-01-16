package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_TB")
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String grade;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAJORID") // 조인할 테이블 컬럼명
    private Major major;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
