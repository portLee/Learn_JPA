package com.example.jpa;

import com.example.jpa.entity.Address;
import com.example.jpa.entity.Intro;
import com.example.jpa.entity.Writer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Writer writer = new Writer("이름",
                    new Intro("text/plain", "소개글"),
                    new Address("주소1", "주소2", "12345"));
            entityManager.persist(writer);

            entityManager.flush();
            entityManager.clear();

            Writer foundWriter = entityManager.find(Writer.class, writer.getId());
            foundWriter.setAddress(new Address("새주소1", "새주소2", "12345"));

            entityManager.remove(foundWriter);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
