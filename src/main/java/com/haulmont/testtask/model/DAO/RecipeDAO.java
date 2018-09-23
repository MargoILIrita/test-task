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
        return entityManager.merge((Recipe) recipe);
    }

    public Recipe getEntity(long id){
        return entityManager.find(Recipe.class, id);
    }

    public Recipe addEntity(DTO entity){
         Recipe recipe = (Recipe)entity;
        entityManager.persist(recipe);
        entityManager.refresh(recipe);
        return recipe;
    }
    public void deleteEntity(long id){
        entityManager.remove(entityManager.find(Recipe.class, id));
    }

    @Override
    public List getList() {
        return  (List) entityManager.createQuery("from Recipe", Recipe.class)
                                    .getResultList();
    }
}
