package com.example.jpa;

import com.example.jpa.entity.GrantedPermission;
import com.example.jpa.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Set;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            GrantedPermission grantedPermission = new GrantedPermission();
            Role role = new Role("R1", "관리자", Set.of(grantedPermission));
            entityManager.persist(role);

            entityManager.flush();
            entityManager.clear();

            Role foundRole = entityManager.find(Role.class, role.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
