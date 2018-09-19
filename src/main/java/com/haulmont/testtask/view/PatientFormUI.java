package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.PatientDAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientFormUI extends UI {
    private Patient patient;

    @Override
    protected void init(VaadinRequest request) {
        DAO dao = null;
        try {
            dao = DAO.getImplementation(Patient.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        patient = (Patient) dao.getEntity(Long.getLong(request.getParameter("id")));

        FormLayout layout = new FormLayout();
        TextField textField = new TextField("Surname");
        //textField.setValue();
        layout.addComponent(textField);
        layout.addComponent(new Label());
        setContent(layout);
    }
}
