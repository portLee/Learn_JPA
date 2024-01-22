package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doc")
public class Document {
    @Id
    private String id;
    private String title;
    private String content;

    @OneToMany
    @JoinColumn(name = "doc_id")
    @MapKeyColumn(name = "doc_title") // Map의 키가 될 컬럼 지정
    private Map<String, PropValue> props = new HashMap<>();

    public void addProp(String key, PropValue propValue) { // 추가 및 수정
        props.put(key, propValue);
    }
    public void removeProp(String key) { // 삭제
        props.remove(key);
    }
}
