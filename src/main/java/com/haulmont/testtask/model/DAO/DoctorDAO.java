package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Recipe;
import org.hibernate.Criteria;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
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

    public int getStatistic(long id){
        String hql = "SELECT COUNT (id) "
                             + "FROM Recipe where DOCTOR_ID = :id";

        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        List<Long> results = query.getResultList();
        return Math.toIntExact(results.iterator().next());

    }
}
