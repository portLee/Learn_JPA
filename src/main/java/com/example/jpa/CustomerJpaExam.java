package com.example.jpa;

import com.example.jpa.entity.Major;
import com.example.jpa.entity.Student;
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
            Major major = new Major("Computer Science", "College of Engineering");
            entityManager.persist(major);

            Student student = new Student("Kim", "3");
            student.setMajor(major);
            entityManager.persist(student);

            entityManager.flush();
            entityManager.clear();

            // Student 검색
            Student foundStudent = entityManager.find(Student.class, 1);
            // Student의 전공 이름 검색
            System.out.println(foundStudent.getMajor().getName());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
