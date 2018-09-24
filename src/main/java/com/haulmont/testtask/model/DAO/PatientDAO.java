package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Patient;

import java.util.List;

public class PatientDAO extends DAO {

     PatientDAO() {
        try {
            super.init();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List getList(){
        return  (List) entityManager.createQuery("from Patient ", Patient.class)
                            .getResultList();
    }

    public Patient changeEntity(DTO patient){
        entityManager.getTransaction().begin();
        Patient patient1 = entityManager.merge((Patient) patient);
        entityManager.getTransaction().commit();
        return patient1;

    }

    public Patient getEntity(long id){
        return entityManager.find(Patient.class, id);
    }

    public Patient addEntity(DTO entity){
        System.out.println("addEntity");
        Patient patient = (Patient)entity;
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        System.out.println(patient);
        entityManager.refresh(patient);
        return patient;
    }
    public void deleteEntity(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Patient.class, id));
        entityManager.getTransaction().commit();
    }
}
