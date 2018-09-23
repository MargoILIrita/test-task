package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Doctor;

import java.util.List;

public class DoctorDAO extends DAO {

     DoctorDAO() {
        try {
            super.init();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Doctor changeEntity(DTO doc){
         entityManager.getTransaction().begin();
         Doctor doctor = entityManager.merge((Doctor) doc);
         entityManager.getTransaction().commit();
         return doctor;
    }

    public Doctor getEntity(long id){
        return entityManager.find(Doctor.class, id);
    }

    public Doctor addEntity(DTO entity){
         Doctor doc = (Doctor)entity;
        entityManager.getTransaction().begin();
        entityManager.persist(doc);
        entityManager.getTransaction().commit();
        entityManager.refresh(doc);
        return doc;
    }
    public void deleteEntity(long id){
         entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Doctor.class, id));
        entityManager.getTransaction().commit();
    }


    @Override
    public List getList() {
        return  (List) entityManager.createQuery("from Doctor", Doctor.class)
                                    .getResultList();
    }
}
