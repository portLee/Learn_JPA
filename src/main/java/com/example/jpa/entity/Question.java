package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    private String id;
    private String text;

    @ElementCollection
    @CollectionTable(
            name = "question_choice",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @OrderColumn(name = "idx") // 인덱스 값 컬럼 지정
    @Column(name = "text")
    private List<String> choices;
}
