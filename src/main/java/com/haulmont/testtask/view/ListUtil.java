package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.DoctorDAO;
import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import static com.haulmont.testtask.Constants.DOCTOR;
import static com.haulmont.testtask.Constants.PATIENT;

@Theme(ValoTheme.THEME_NAME)
public class ListUtil {
    private  DAO dao = null;
    Class tClass;

    public ListUtil(Class tClass) {
        this.tClass = tClass;
        try {
            dao = DAO.getImplementation(tClass);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public  Table printTable(){
        BeanItemContainer beanItemContainer = new BeanItemContainer<>(tClass);
        beanItemContainer.addAll(dao.getList());

        switch (tClass.getName()){
            case PATIENT:
                return patientTable(beanItemContainer);
            case DOCTOR:
                return doctorTable(beanItemContainer);
        }
        return null;
    }

    private  Table doctorTable(BeanItemContainer beanItemContainer){
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
                return ((DoctorDAO)dao).getStatistic(((Doctor) itemId).getId());
            }
        });
        table.setSelectable(true);
        return table;
    }

    private  Table patientTable(BeanItemContainer beanItemContainer){
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

    public  void deleteAction(DTO patient, Container container){
        dao.deleteEntity(patient.getId());
        BeanItemContainer container1 = (BeanItemContainer)container;
        container1.removeAllItems();
        container1.addAll(dao.getList());

    }

    public static Window getImplementation(Class cl, DTO dto, Container beanItemContainer) throws NoSuchMethodException {
        switch (cl.getName()) {
            case PATIENT:
                return new PatientFormUI((Patient) dto, beanItemContainer);
            case DOCTOR:
                return new DoctorFormUI((Doctor) dto, beanItemContainer);
            //case RECIPE:
            //  return new RecipeDAO();
        }
        throw new NoSuchMethodException("No such implementation " + cl.getName());
    }

}