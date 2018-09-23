package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientFormUI extends Window {
    private Patient patient = null;
    private DAO dao = null;
    private BeanItemContainer container;

    public  PatientFormUI(Patient patient, Container container) {
        this.container = (BeanItemContainer)container;
        try {
            dao = DAO.getImplementation(Patient.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(patient != null) this.patient = patient;

        FormLayout layout = printComponent();
        setContent(layout);
    }

    public PatientFormUI(Container container){
        this(null, container);
    }

    private FormLayout printComponent(){
        FormLayout layout = new FormLayout();
        TextField surname = new TextField("Last Name");
        surname.setValue(patient != null ? patient.getLastName() : "");
        surname.setRequired(true);
        layout.addComponent(surname);

        TextField name = new TextField("First Name");
        name.setValue(patient != null ? patient.getName() : "");
        name.setRequired(true);
        name.setResponsive(false);
        layout.addComponent(name);

        TextField patronymic = new TextField("Patronymic");
        patronymic.setValue(patient != null ? patient.getPatronymic():"");
        patronymic.setResponsive(false);
        layout.addComponent(patronymic);

        TextField phone = new TextField("Phone");
        phone.setDescription("7 9XX XXXXXXX");
        phone.setValue(patient != null ? patient.getPhone_number():"7");
        phone.addValidator(new RegexpValidator("7 \\d{3} \\d{7}","Please, enter valid phone number"));
        phone.setResponsive(false);
        layout.addComponent(phone);

        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            System.out.println("addClickListener");
            if (patient == null){
                finalDao.addEntity(new Patient(name.getValue(), surname.getValue(), patronymic.getValue(), phone.getValue().substring(1)));
            }
            else {
                finalDao.changeEntity(new Patient(patient.getId(),name.getValue(), surname.getValue(),
                                                  patronymic.getValue(), phone.getValue().substring(1)));
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