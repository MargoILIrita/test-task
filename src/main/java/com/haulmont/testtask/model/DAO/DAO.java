package com.haulmont.testtask.model.DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Created by Маргарита on 28.05.2016.
 */
public abstract class DAO {
    protected static EntityManagerFactory entityManagerFactory;

    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("ru.easyjava.data.jpa.hibernate");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("");
    }
}
