package com.haulmont.testtask;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.DoctorDAO;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        DAO dao = new DoctorDAO();
        dao.init();
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        layout.addComponent(new Label("Main UI test"));

        setContent(layout);
    }
}