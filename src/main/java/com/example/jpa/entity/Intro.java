package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Intro {
    @Column(table = "writer_intro", name = "content_type")
    private String contentType;

    @Column(table = "writer_intro")
    private String content;
}
