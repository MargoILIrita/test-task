package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.Contact;

import javax.persistence.EntityManager;
import java.util.*;

public class ImDAO extends DAO {
    public List test(){
        Contact a = new Contact();
        a.setEmail("trash");
        a.setName("trash");
         a.setNumber("trash");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        List res = (List) em.createQuery("from Contact", Contact.class)
                            .getResultList();
        em.close();
        return res;
    }
}
