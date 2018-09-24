package com.haulmont.testtask;

import com.haulmont.testtask.model.Entities.DTO;
import com.haulmont.testtask.model.Entities.Doctor;
import com.haulmont.testtask.model.Entities.Patient;
import com.haulmont.testtask.model.Entities.Recipe;
import com.haulmont.testtask.view.ListUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;



@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(printListTab(Patient.class), "Patient List");
        tabSheet.addTab(printListTab(Doctor.class), "Doctor List");
        tabSheet.addTab(printListTab(Recipe.class),"Recipes List");
        setContent(tabSheet);
    }

    private FormLayout printListTab(Class tClass){
        ListUtil listUtil = new ListUtil(tClass);
        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        HorizontalLayout layout1 = new HorizontalLayout();

        Button add = new Button("Add");
        layout1.addComponent(add);
        Button change = new Button("Change");
        layout1.addComponent(change);
        Button delete = new Button("Delete");
        layout1.addComponent(delete);

        layout.addComponent(layout1);

        Table table = listUtil.printTable();
        layout.addComponent(table);

        add.addClickListener((Button.ClickListener) event -> {
            try {
                addWindow(ListUtil.getImplementation(tClass, null, table.getContainerDataSource()));
            }
            catch (NoSuchMethodException e) {
                Notification.show(String.valueOf(this), e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
        change.addClickListener((Button.ClickListener) event -> {
            try {
                addWindow(ListUtil.getImplementation(tClass, (DTO)table.getValue(), table.getContainerDataSource()));
            }
            catch (NoSuchMethodException e) {
                Notification.show(String.valueOf(this), e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
        delete.addClickListener((Button.ClickListener)  event ->{
            listUtil.deleteAction((DTO)table.getValue(), table.getContainerDataSource());
        });
        return layout;
    }
}