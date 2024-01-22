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

    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<Permission> permissions = new HashSet<>();

    public void addPerm(Permission perm) { // 추가
        permissions.add(perm);
    }
    public void removePerm(Permission perm) { // 삭제
        permissions.remove(perm);
    }
    public void removeAllPerm() { // 모두 삭제
        permissions.clear();
    }
}
