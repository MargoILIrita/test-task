package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Locale;

@Theme(ValoTheme.THEME_NAME)
public class PatientFormUI extends AbstractHumanFormUI {
    private DAO dao = null;

    private TextField phone;

    public  PatientFormUI(Patient patient, Container container) {
        super(patient,container);
        try {
            dao = DAO.getImplementation(Patient.class);
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
        printPhone(layout);
        printButtons(layout);
        return layout;
    }

    private void printPhone(Layout layout){
        phone = new TextField("Phone");
        phone.setDescription("9XXXXXXXXX");
        phone.setValue(human != null ? ((Patient)human).getPhone_number():"");
        phone.setConverter(new PhoneConverter());
        phone.addValidator(new RegexpValidator("9\\d{9}","Please, enter valid phone number"));
        layout.addComponent(phone);
    }

    protected void printButtons(Layout layout){
        Button ok = new Button("OK");
        DAO finalDao = dao;
        ok.addClickListener((Button.ClickListener) event -> {
            phone.validate();
            surname.validate();
            name.validate();
            if (human == null){
                finalDao.addEntity(new Patient(name.getValue(), surname.getValue(), patronymic.getValue(),
                                               (String) phone.getConverter().convertToModel(
                                                       phone.getValue(), String.class, Locale.ROOT)));
            }
            else {
                finalDao.changeEntity(new Patient(human.getId(),name.getValue(), surname.getValue(),
                                                  patronymic.getValue(), (String) phone.getConverter().convertToModel(
                        phone.getValue(), String.class, Locale.ROOT)));
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
