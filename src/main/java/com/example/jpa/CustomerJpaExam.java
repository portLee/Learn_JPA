package com.example.jpa;

import com.example.jpa.entity.Permission;
import com.example.jpa.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            // Step 1: Permission 엔티티 저장
            Permission perm1 = new Permission("P1", "G1");
            Permission perm2 = new Permission("P2", "G2");
            Permission perm3 = new Permission("P3", "G3");
            entityManager.persist(perm1);
            entityManager.persist(perm2);
            entityManager.persist(perm3);

            // Step 2: Role 엔티티 생성 및 저장
            Set<Permission> permissions = new HashSet<>();
            permissions.add(perm1);
            permissions.add(perm2);
            Role role = new Role("R1", "ROLE1", permissions);
            entityManager.persist(role);

            entityManager.flush();
            entityManager.clear();

            Permission foundPerm1 = entityManager.find(Permission.class, "P1");
            Permission foundPerm3 = entityManager.find(Permission.class, "P3");
            Role foundRole = entityManager.find(Role.class, "R1");
//            foundRole.removePerm(foundPerm1);
//            foundRole.addPerm(foundPerm3);
            foundRole.removeAllPerm();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
