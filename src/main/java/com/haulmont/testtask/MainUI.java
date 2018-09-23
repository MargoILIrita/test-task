package com.haulmont.testtask;

import com.haulmont.testtask.model.Entities.Patient;
import com.haulmont.testtask.view.PatientFormUI;
import com.haulmont.testtask.view.PatientListUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(patientListTab(), "Patient List");
        setContent(tabSheet);
    }

    private FormLayout patientListTab(){
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

        Table table = PatientListUtil.printTable();
        layout.addComponent(table);

        add.addClickListener((Button.ClickListener) event -> {
            addWindow(new PatientFormUI(table.getContainerDataSource()));
        });
        change.addClickListener((Button.ClickListener) event -> {
            addWindow(new PatientFormUI((Patient)table.getValue(), table.getContainerDataSource()));
        });
        delete.addClickListener((Button.ClickListener)  event ->{
            PatientListUtil.deleteAction((Patient)table.getValue());
            getPage().reload();
        });
        return layout;
    }
}