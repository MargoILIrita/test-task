package com.haulmont.testtask.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;

public abstract class DoctorDAO extends DAO {

    private EntityManager entityManager;

    public DoctorDAO() {
        entityManager = entityManagerFactory.createEntityManager();
    }


}
