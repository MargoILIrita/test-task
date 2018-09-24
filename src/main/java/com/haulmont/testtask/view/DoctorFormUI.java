package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class DoctorFormUI extends AbstractHumanFormUI {
    private TextField specialization;

    public  DoctorFormUI(Doctor doctor, Container container) {
        super(doctor,container);
        try {
            dao = DAO.getImplementation(Doctor.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        FormLayout layout = printComponent();
        setContent(layout);
    }


    protected FormLayout printComponent(){
        FormLayout layout = new FormLayout();
        printName(layout);
        printSpecialization(layout);
        printButtons(layout);
        return layout;
    }

    private void printSpecialization(Layout layout){
        specialization = new TextField("Specialization");
        specialization.setValue(human != null ? ((Doctor)human).getSpecialization(): "");
        specialization.setRequired(true);
        layout.addComponent(specialization);
    }

    protected void printButtons(Layout layout){
        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            surname.validate();
            name.validate();
            if (human == null){
                finalDao.addEntity(new Doctor(name.getValue(), surname.getValue(), patronymic.getValue(), specialization.getValue()));
            }
            else {
                finalDao.changeEntity(new Doctor(human.getId(),name.getValue(), surname.getValue(),
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
    }
}
