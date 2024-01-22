package com.example.jpa;

import com.example.jpa.entity.Choice;
import com.example.jpa.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Choice c1 = new Choice("C1", "TEXT1");
            Choice c2 = new Choice("C2", "TEXT2");
            Choice c3 = new Choice("C3", "TEXT3");
            Choice c4 = new Choice("C4", "TEXT4");
            entityManager.persist(c1);
            entityManager.persist(c2);
            entityManager.persist(c3);
            entityManager.persist(c4);

            List<Choice> choices = new ArrayList<>();
            choices.add(c1);
            choices.add(c2);
            choices.add(c3);
            entityManager.persist(new Question("Q1", "질문", choices));

            entityManager.flush();
            entityManager.clear();

            Choice foundChoice1 = entityManager.find(Choice.class, "C1");
            Choice foundChoice4 = entityManager.find(Choice.class, "C4");
            Question foundQuestion = entityManager.find(Question.class, "Q1");
            foundQuestion.removeChoice(foundChoice1);
            foundQuestion.addChoice(foundChoice4);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
