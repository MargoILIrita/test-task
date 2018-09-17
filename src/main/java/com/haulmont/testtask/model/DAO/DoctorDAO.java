package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.Doctor;

import javax.persistence.EntityManager;

public abstract class DoctorDAO extends DAO {

    private EntityManager entityManager;

    public DoctorDAO() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Doctor changeDoctor(Doctor doc){
        return entityManager.merge(doc);
    }

    public Doctor getDoctor(long id){
        return entityManager.find(Doctor.class, id);
    }

    public Doctor addDoctor(Doctor doc){
        entityManager.persist(doc);
        entityManager.refresh(doc);
        return doc;
    }
    public void deleteDoctor(Doctor doc){
        entityManager.remove(doc);
    }




}
