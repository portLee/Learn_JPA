package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "best_pick")
public class BestPick {
    @Id
    @Column(name = "user_email")
    private String email;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_email")
    private User user;
    private String title;

    public BestPick(User user, String title) {
        this.email = user.getEmail();
        this.user = user;
        this.title = title;
    }
}
