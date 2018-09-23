package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class DoctorFormUI extends Window {
    private Doctor doctor = null;
    private DAO dao = null;
    private BeanItemContainer container;

    public  DoctorFormUI(Doctor doctor, Container container) {
        this.container = (BeanItemContainer)container;
        try {
            dao = DAO.getImplementation(Doctor.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(doctor != null) this.doctor = doctor;

        FormLayout layout = printComponent();
        setContent(layout);
    }

    public DoctorFormUI(Container container){
        this(null, container);
    }

    private FormLayout printComponent(){
        FormLayout layout = new FormLayout();
        TextField surname = new TextField("Last Name");
        surname.setValue(doctor != null ? doctor.getLastName() : "");
        surname.setRequired(true);
        layout.addComponent(surname);

        TextField name = new TextField("First Name");
        name.setValue(doctor != null ? doctor.getName() : "");
        name.setRequired(true);
        layout.addComponent(name);

        TextField patronymic = new TextField("Patronymic");
        patronymic.setValue(doctor != null ? doctor.getPatronymic():"");
        layout.addComponent(patronymic);

        TextField specialization = new TextField("Specialization");
        specialization.setValue(doctor != null ? doctor.getSpecialization(): "");
        specialization.setRequired(true);
        layout.addComponent(specialization);

        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            System.out.println("addClickListener");
            if (doctor == null){
                finalDao.addEntity(new Doctor(name.getValue(), surname.getValue(), patronymic.getValue(), specialization.getValue()));
            }
            else {
                finalDao.changeEntity(new Doctor(doctor.getId(),name.getValue(), surname.getValue(),
                                                  patronymic.getValue(), specialization.getValue()));
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
        layout.setResponsive(false);
        return layout;
    }
}
