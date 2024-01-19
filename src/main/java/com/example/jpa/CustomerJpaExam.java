package com.example.jpa;

import com.example.jpa.entity.MembershipCard;
import com.example.jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            User user = new User("a@a.com", "이름", LocalDateTime.now());
            entityManager.persist(user);

            MembershipCard membershipCard = new MembershipCard("8888111122223333", user);
            entityManager.persist(membershipCard);

            entityManager.flush();
            entityManager.clear();

            MembershipCard foundCard = entityManager.find(MembershipCard.class, "8888111122223333");
            System.out.println(foundCard.getOwner());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
