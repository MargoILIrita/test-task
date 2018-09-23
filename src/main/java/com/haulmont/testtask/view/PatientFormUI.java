package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientFormUI extends UI {
    private Patient patient = null;
    private String returnURL = "";

    @Override
    protected void init(VaadinRequest request) {
         DAO dao = null;
        try {
            dao = DAO.getImplementation(Patient.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(request.getParameter("id") != null) patient = (Patient) dao.getEntity(Long.parseLong(request.getParameter("id")));
        returnURL = request.getParameter("return");

        FormLayout layout = new FormLayout();
        TextField surname = new TextField("Surname");
        surname.setValue(patient != null ? patient.getSurname():"");
        surname.setRequired(true);
        //surname.set();
        layout.addComponent(surname);

        TextField name = new TextField("Name");
        name.setValue(patient != null ? patient.getName():"");
        name.setRequired(true);
        layout.addComponent(name);

        TextField patronymic = new TextField("Patronymic");
        patronymic.setValue(patient != null ? patient.getPatronymic():"");
        layout.addComponent(patronymic);

        TextField phone = new TextField("Phone");
        phone.setDescription("79XXXXXXXXX");
        phone.setValue(patient != null ? "7" + patient.getPhone_number():"7");
        phone.addValidator(new RegexpValidator("7\\d{10}","Please, enter valid phone number"));
        layout.addComponent(phone);

        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            if (patient == null){
                finalDao.addEntity(new Patient(name.getValue(), surname.getValue(), patronymic.getValue(), phone.getValue()));
            }
            else {
                finalDao.changeEntity(new Patient(patient.getId(),name.getValue(), surname.getValue(), patronymic.getValue(), phone.getValue()));
            }
            getPage().setLocation("/" + returnURL);
        });
        Button cancel = new Button("Cancel");
        cancel.addClickListener((Button.ClickListener)  event ->{
            getPage().setLocation("/" + returnURL);
        });
        layout.addComponent(ok);
        layout.addComponent(cancel);
        setContent(layout);
    }
}
