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

    @ElementCollection
    @CollectionTable(
            name = "doc_prop",
            joinColumns = @JoinColumn(name = "doc_id")
    )
    @MapKeyColumn(name = "name") // 키가 될 컬럼 지정
    @Column(name = "`value`")
    private Map<String, String> props = new HashMap<>();

    public void setProp(String name, String value) { // 추가 및 수정
        props.put(name, value);
    }
    public void removeProp(String name) { // 삭제
        props.remove(name);
    }
}
