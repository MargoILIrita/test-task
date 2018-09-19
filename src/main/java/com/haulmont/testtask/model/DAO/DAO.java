package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.DTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Created by Маргарита on 28.05.2016.
 */
public abstract class DAO {
    protected static EntityManagerFactory entityManagerFactory;
    private final static String PATIENT = "com.haulmont.testtask.model.Entities.Patient";
    private final static String DOCTOR = "com.haulmont.testtask.model.Entities.Doctor";
    private final static String RECIPE = "com.haulmont.testtask.model.Entities.Recipe";

    protected EntityManager entityManager;

public void init(){
    if (entityManagerFactory == null) {
        entityManagerFactory = Persistence.createEntityManagerFactory("ru.easyjava.data.jpa.hibernate");
    }
    }

    public abstract List getList();

    public abstract DTO getEntity(long id);

    public abstract DTO changeEntity(DTO entity);

    public abstract void deleteEntity(long id);

    public abstract DTO addEntity(DTO entity);

    public static DAO getImplementation(Class cl) throws NoSuchMethodException {
        switch(cl.getName()){
            case PATIENT:
                return new PatientDAO();
            case DOCTOR:
                return new DoctorDAO();
            case RECIPE:
                return new RecipeDAO();
        }
        throw new NoSuchMethodException("No such implementation " + cl.getName());
    }
}
