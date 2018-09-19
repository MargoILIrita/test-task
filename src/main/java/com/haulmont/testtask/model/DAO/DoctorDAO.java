package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.Doctor;

import javax.persistence.EntityManager;

public class DoctorDAO extends DAO {

    public DoctorDAO() {
        try {
            super.init();
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
