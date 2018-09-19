package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.Recipe;

public class RecipeDAO extends DAO {

    public RecipeDAO() {
        try {
            super.init();
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Recipe changePatient(Recipe recipe){
        return entityManager.merge(recipe);
    }

    public Recipe getPatient(long id){
        return entityManager.find(Recipe.class, id);
    }

    public Recipe addPatient(Recipe recipe){
        entityManager.persist(recipe);
        entityManager.refresh(recipe);
        return recipe;
    }
    public void deleteDoctor(Recipe recipe){
        entityManager.remove(recipe);
    }
}
