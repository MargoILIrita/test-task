package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Patient;
import com.haulmont.testtask.model.Entities.Priority;
import com.haulmont.testtask.model.Entities.Recipe;
import com.haulmont.testtask.model.Entities.Validity;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;

@Theme(ValoTheme.THEME_NAME)
public class RecipeFormUI extends Window {

    private Recipe recipe = null;
    private DAO dao = null;
    private BeanItemContainer container;

    public RecipeFormUI(Recipe recipe, Container container) {
        this.container = (BeanItemContainer)container;
        try {
            dao = DAO.getImplementation(Recipe.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(recipe != null) this.recipe = recipe;

        FormLayout layout = printComponent();
        setContent(layout);
    }

    private FormLayout printComponent() {
        FormLayout layout = new FormLayout();

        BeanItemContainer docContainer = new BeanItemContainer(Doctor.class);
        try {
            docContainer.addAll(DAO.getImplementation(Doctor.class).getList());
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ComboBox selectDoc = new ComboBox("Select Doctor", docContainer);
        selectDoc.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        selectDoc.setNullSelectionAllowed(false);
        selectDoc.setItemCaptionPropertyId("lastName");
        selectDoc.setValue(recipe == null ? null : recipe.getDoctor());
        layout.addComponent(selectDoc);

        BeanItemContainer patientContainer = new BeanItemContainer(Patient.class);
        try {
            patientContainer.addAll(DAO.getImplementation(Patient.class).getList());
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }

        ComboBox selectPatient = new ComboBox("Select Patient", patientContainer);
        selectPatient.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        selectPatient.setItemCaptionPropertyId("lastName");
        selectPatient.setNullSelectionAllowed(false);
        selectDoc.setValue(recipe == null ? null : recipe.getPatient());
        layout.addComponent(selectPatient);

        TextArea description = new TextArea("Description");
        description.setValue(recipe == null ? "" : recipe.getDescription());
        layout.addComponent(description);

        DateField date = new DateField("Creation date");
        date.setDateFormat("dd.mm.yyyy");
        date.setValue(recipe == null ? null : recipe.getDate());
        layout.addComponent(date);

        IndexedContainer validityContainer = new IndexedContainer(Arrays.asList(Validity.values()));
        ComboBox validity = new ComboBox("Priority", validityContainer);
        validity.setValue(recipe == null? validityContainer.getItem(1): recipe.getValidity());
        validity.setNullSelectionAllowed(false);
        layout.addComponent(validity);

        IndexedContainer priotityContainer = new IndexedContainer(Arrays.asList(Priority.values()));
        ComboBox priority = new ComboBox("Priority", priotityContainer);
        priority.setValue(recipe == null? priotityContainer.getItem(1): recipe.getPriority());
        priority.setNullSelectionAllowed(false);
        layout.addComponent(priority);

        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            if (recipe == null){
                finalDao.addEntity(new Recipe((Doctor) selectDoc.getValue(),
                                              (Patient) selectPatient.getValue(),
                                              description.getValue(),
                                              date.getValue(),
                                              (Validity) validity.getValue(),
                                              (Priority) priority.getValue()));
            }
            else {
                finalDao.changeEntity(new Recipe(recipe.getId(),
                                                 (Doctor) selectDoc.getValue(),
                                                 (Patient) selectPatient.getValue(),
                                                 description.getValue(),
                                                 date.getValue(),
                                                 (Validity) validity.getValue(),
                                                 (Priority) priority.getValue()));
            }
            container.removeAllItems();
            container.addAll(finalDao.getList());
            this.close();
        });
        Button cancel = new Button("Cancel");
        cancel.addClickListener((Button.ClickListener)  event ->{
            this.close();
        });
        layout.addComponent(ok);
        layout.addComponent(cancel);

        return layout;


    }

}
