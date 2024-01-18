package com.example.jpa;

import com.example.jpa.entity.Document;
import com.example.jpa.entity.GrantedPermission;
import com.example.jpa.entity.Question;
import com.example.jpa.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            Map<String, String> props = new HashMap<>();
            props.put("p1", "v1");
            props.put("p2", "v2");
            Document document = new Document("M1", "제목", "내용", props);
            entityManager.persist(document);

            entityManager.flush();
            entityManager.clear();

            Document foundDocument = entityManager.find(Document.class, document.getId());
            foundDocument.setProp("p1", "newV1"); // 수정
            foundDocument.setProp("p10", "v10"); // 추가
            foundDocument.removeProp("p2"); // 삭제

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
