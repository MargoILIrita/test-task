package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientListUtil {
    private static DAO dao = null;


    public static Table printTable(){
        try {
            dao = DAO.getImplementation(Patient.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        BeanItemContainer<Patient> beanItemContainer = new BeanItemContainer<>(Patient.class);
        beanItemContainer.addAll(dao.getList());
        Table table = new Table("Patients",beanItemContainer);
        table.setPageLength(table.size());
        table.setColumnHeader("name", "First Name");
        table.setColumnHeader("lastName", "Last Name");
        table.setColumnHeader("patronymic", "Patronymic");
        table.setColumnHeader("phone_number", "Phone");
        table.setColumnHeader("id", "#");
        table.setSelectable(true);

        return table;
    }

    public static void deleteAction(Patient patient){
        dao.deleteEntity(patient.getId());
    }
}