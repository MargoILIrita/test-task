package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.DoctorDAO;
import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Patient;
import com.haulmont.testtask.model.Entities.Recipe;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Locale;

import static com.haulmont.testtask.Constants.DOCTOR;
import static com.haulmont.testtask.Constants.PATIENT;
import static com.haulmont.testtask.Constants.RECIPE;

@Theme(ValoTheme.THEME_NAME)
public class ListUtil {
    private DAO dao = null;
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
            case RECIPE:
                return recipesTable(beanItemContainer);
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

    private Table recipesTable(BeanItemContainer beanItemContainer){
        Table table = new Table("Recipes", beanItemContainer);
        table.setPageLength(table.size());
        table.setColumnHeader("doctor", "Doctor ID");
        table.setColumnHeader("patient", "Patient ID");
        table.setColumnHeader("description", "Description");
        table.setColumnHeader("date", "Creation date ");
        table.setColumnHeader("validity", "Validity");
        table.setColumnHeader("priority", "Priority");
        setConverters(table, beanItemContainer);
        table.setVisibleColumns(new Object[]{"priority", "doctor", "patient", "description", "date", "validity" });
        table.setSelectable(true);
        return table;
    }

    private void setConverters(Table table, BeanItemContainer beanItemContainer){
        table.setConverter("doctor", new Converter<String, Doctor>() {
            @Override
            public Doctor convertToModel(String value, Class<? extends Doctor> targetType, Locale locale) throws ConversionException {
                return (Doctor) beanItemContainer.getItem(Long.valueOf(value.split(" ")[1])).getBean();
            }

            @Override
            public String convertToPresentation(Doctor value, Class<? extends String> targetType, Locale locale) throws ConversionException {
                return value.getLastName() + " " + value.getId();
            }

            @Override
            public Class<Doctor> getModelType() {
                return Doctor.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
        table.setConverter("patient", new Converter<String, Patient>() {
            @Override
            public Patient convertToModel(String value, Class<? extends Patient> targetType, Locale locale) throws ConversionException {
                return (Patient) beanItemContainer.getItem(Long.valueOf(value.split(" ")[1])).getBean();
            }

            @Override
            public String convertToPresentation(Patient value, Class<? extends String> targetType, Locale locale) throws ConversionException {
                return value.getLastName() + " " + value.getId();
            }

            @Override
            public Class<Patient> getModelType() {
                return Patient.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public void deleteAction(DTO patient, Container container){
        try {
            dao.deleteEntity(patient.getId());
        }
        catch (Exception e){
            Notification.show("Enable to delete entity. Recipe for id = " + patient.getId() +" exist.",
                              Notification.Type.ERROR_MESSAGE);
        }
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
            case RECIPE:
                return new RecipeFormUI((Recipe)dto, beanItemContainer);
        }
        throw new NoSuchMethodException("No such implementation " + cl.getName());
    }

}