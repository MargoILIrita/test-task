package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class PatientListUI extends UI{

    private static final String THISPAGE  =  "";
    private String phoneformat = "+7 (%s) %s";
    private Object tableValue;

    @Override
    protected void init(VaadinRequest request) {
        DAO dao = null;
        try {
            dao = DAO.getImplementation(Patient.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        HorizontalLayout layout1 = new HorizontalLayout();

        Button add = new Button("Add");
        add.addClickListener((Button.ClickListener) event -> {
            getPage().setLocation(String.format("/add?return=%s",THISPAGE));

        });
        layout1.addComponent(add);
        Button change = new Button("Change");
        change.addClickListener((Button.ClickListener) event -> {
            getPage().setLocation(String.format("/add?id=%s&return=%s",tableValue, THISPAGE));

        });
        layout1.addComponent(change);
        layout1.addComponent(new Button("Delete"));

        layout.addComponent(layout1);


        layout.addComponent(printTable(dao));

        setContent(layout);
    }

    private Table printTable(DAO dao){
        Table table = new Table("Patients");
        table.addContainerProperty("Surname", String.class, null);
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Patronymic", String.class, null);
        table.addContainerProperty("Phone", String.class, null);

        for (Object con: dao.getList()
        ) {
            Patient c = (Patient) con;
            table.addItem(new Object[]{c.getSurname(), c.getName(), c.getPatronymic(),
                                       String.format(phoneformat,c.getPhone_number().substring(0,3),
                                                     c.getPhone_number().substring(4))}, c.getId());
        }


        table.setPageLength(table.size());
        table.setSelectable(true);
        table.setImmediate(true);

        table.addValueChangeListener((Property.ValueChangeListener) event -> {
    tableValue = table.getValue();
        });
        return table;
    }
}