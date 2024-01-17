package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@SecondaryTables({
        @SecondaryTable(
                name = "writer_address",
                pkJoinColumns = @PrimaryKeyJoinColumn(
                        name = "writer_id",
                        referencedColumnName = "id"
                )
        ),
        @SecondaryTable(
                name = "writer_intro", // 사용할 다른 테이블 지정
                pkJoinColumns = @PrimaryKeyJoinColumn( // 테이블 참조 컬럼 지정
                        name = "writer_id", // writer_intro 테이블 컬럼
                        referencedColumnName = "id" // writer 테이블 컬럼
                )
        )
})
public class Writer {
    @Id
    private int id;
    private String name;

    @Embedded
    private Intro intro;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1",
                    column = @Column(table = "writer_address", name = "addr1")),
            @AttributeOverride(name = "address2",
                    column = @Column(table = "writer_address", name = "addr2")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(table = "writer_address"))
    })
    private Address address;

    public Writer(String name, Intro intro, Address address) {
        this.name = name;
        this.intro = intro;
        this.address = address;
    }
}
