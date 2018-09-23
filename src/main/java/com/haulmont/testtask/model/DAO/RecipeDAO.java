package com.haulmont.testtask.model.DAO;

import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Recipe;

import java.util.List;

public class RecipeDAO extends DAO {

     RecipeDAO() {
        try {
            super.init();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Recipe changeEntity(DTO recipe){
        entityManager.getTransaction().begin();
        Recipe recipe1 = entityManager.merge((Recipe) recipe);
        entityManager.getTransaction().commit();
        return recipe1;
    }

    public Recipe getEntity(long id){
        return entityManager.find(Recipe.class, id);
    }

    public Recipe addEntity(DTO entity){
        entityManager.getTransaction().begin();
        Recipe recipe = (Recipe)entity;
        entityManager.persist(recipe);
        entityManager.getTransaction().commit();
        entityManager.refresh(recipe);
        return recipe;
    }
    public void deleteEntity(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Recipe.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public List getList() {
        return  (List) entityManager.createQuery("from Recipe", Recipe.class)
                                    .getResultList();
    }
}
