package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.DoctorDAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientListUtil {
    private static DAO dao = null;


    public static Table printTable(Class tClass){
        try {
            dao = DAO.getImplementation(tClass);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        BeanItemContainer beanItemContainer = new BeanItemContainer<>(tClass);
        beanItemContainer.addAll(dao.getList());

        return patientTable(beanItemContainer);
    }

    private static Table doctorTable(BeanItemContainer beanItemContainer){
        Table table = new Table("Doctors",beanItemContainer);
        table.setPageLength(table.size());
        table.setColumnHeader("name", "First Name");
        table.setColumnHeader("lastName", "Last Name");
        table.setColumnHeader("patronymic", "Patronymic");
        table.setColumnHeader("specialization", "Specialization");
        table.setColumnHeader("id", "#");
        table.addGeneratedColumn("Statistic", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                return ((DoctorDAO)dao).getStatistic((Long) itemId);
            }
        });
        table.setSelectable(true);
        return table;
    }

    private static Table patientTable(BeanItemContainer beanItemContainer){
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