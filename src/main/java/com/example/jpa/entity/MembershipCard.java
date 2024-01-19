package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class MembershipCard {
    @Id
    @Column(name = "card_no")
    private String number;

    @OneToOne
    @JoinColumn(name = "user_email")
    private User owner;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    private Boolean enabled;

    public MembershipCard(String number, User owner) {
        this.number = number;
        this.owner = owner;
        this.expiryDate = LocalDateTime.now();
        this.enabled = false;
    }
}
