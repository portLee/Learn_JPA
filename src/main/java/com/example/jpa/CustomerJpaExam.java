package com.example.jpa;

import com.example.jpa.entity.Address;
import com.example.jpa.entity.Hotel;
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
            Address address = null;
            Hotel hotel = new Hotel("H00", "HN", 2022, address);
            entityManager.persist(hotel);

            entityManager.flush();
            entityManager.clear();

            Hotel foundHotel = entityManager.find(Hotel.class, "H00");
            System.out.println(foundHotel.getAddress());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
