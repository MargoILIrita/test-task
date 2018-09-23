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
        return entityManager.merge((Doctor) doc);
    }

    public Doctor getEntity(long id){
        return entityManager.find(Doctor.class, id);
    }

    public Doctor addEntity(DTO entity){
         Doctor doc = (Doctor)entity;
        entityManager.persist(doc);
        entityManager.refresh(doc);
        return doc;
    }
    public void deleteEntity(long id){
        entityManager.remove(entityManager.find(Doctor.class, id));
    }


    @Override
    public List getList() {
        return  (List) entityManager.createQuery("from Doctor", Doctor.class)
                                    .getResultList();
    }
}
