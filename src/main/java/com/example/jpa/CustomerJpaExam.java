package com.example.jpa;

import com.example.jpa.entity.Choice;
import com.example.jpa.entity.Document;
import com.example.jpa.entity.PropValue;
import com.example.jpa.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        try {
            PropValue prop1 = new PropValue("P1", "Value1");
            PropValue prop2 = new PropValue("P2", "Value2");
            PropValue prop3 = new PropValue("P3", "Value3");
            PropValue prop4 = new PropValue("P4", "Value4");
            PropValue prop5 = new PropValue("P5", "Value5");
            entityManager.persist(prop1);
            entityManager.persist(prop2);
            entityManager.persist(prop3);
            entityManager.persist(prop4);
            entityManager.persist(prop5);

            Map<String, PropValue> props = new HashMap<>();
            props.put("Key1", prop1);
            props.put("Key2", prop2);
            props.put("Key3", prop3);
            Document document = new Document("D1", "제목1", "내용1", props);
            entityManager.persist(document);

            entityManager.flush();
            entityManager.clear();

            Document foundDoc = entityManager.find(Document.class, "D1");
            PropValue foundProp4 = entityManager.find(PropValue.class, "P4");
            PropValue foundProp5 = entityManager.find(PropValue.class, "P5");
            foundDoc.addProp("Key1", foundProp4); // Key1 매핑 값을 prop1 -> foundProp4로 변경
            foundDoc.addProp("Key5", foundProp5); // 키 값 Key5 추가
            foundDoc.removeProp("Key2"); // 키 값 Key2 삭제

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
