package com.example.jpa;

import com.example.jpa.entity.GrantedPermission;
import com.example.jpa.entity.Question;
import com.example.jpa.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Question question = new Question("Q1", "질문", List.of("보기1", "보기2"));
            entityManager.persist(question);

            entityManager.flush();
            entityManager.clear();

            Question foundQuestion = entityManager.find(Question.class, question.getId());
            foundQuestion.setChoices(List.of("보기3", "보기4"));
//            entityManager.remove(foundQuestion);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
