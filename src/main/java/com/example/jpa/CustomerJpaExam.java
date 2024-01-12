package com.example.jpa;

import com.example.jpa.entity.Customer;
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
            Customer customer = new Customer("ID0004", "Lee"); // 비영속 상태(new)
            entityManager.persist(customer); // Customer 객체가 영속 상태(Managed)
            entityManager.detach(customer); // Customer 객체를 준영속 상태(Detached)

            Customer foundCustomer = entityManager.find(Customer.class, "ID0004");
            System.out.println(foundCustomer);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
