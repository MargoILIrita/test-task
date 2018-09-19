package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Patient;

import javax.persistence.EntityManager;

public class PatientDAO extends DAO {

    public PatientDAO() {
        try {
            super.init();
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Patient changePatient(Patient patient){
        return entityManager.merge(patient);
    }

    public Patient getPatient(long id){
        return entityManager.find(Patient.class, id);
    }

    public Patient addPatient(Patient patient){
        entityManager.persist(patient);
        entityManager.refresh(patient);
        return patient;
    }
    public void deleteDoctor(Patient patient){
        entityManager.remove(patient);
    }
}
