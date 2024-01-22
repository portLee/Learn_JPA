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

    @OneToMany
    @JoinColumn(name = "question_id")
    @OrderColumn(name = "idx") // 인덱스 값 컬럼 지정
    private List<Choice> choices;

    public void removeChoice(Choice choice) { // 삭제
        choices.remove(choice);
    }
    public void addChoice(Choice choice) { // 추가
        choices.add(choice);
    }
}
