package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "role_perm", // 컬렉션 테이블 지정
            joinColumns = @JoinColumn(name = "role_id") // 조인 컬럼 지정
    )
    private Set<GrantedPermission> permissions = new HashSet<>();
}
