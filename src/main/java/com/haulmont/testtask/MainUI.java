package com.haulmont.testtask;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.DAO.ImDAO;
import com.haulmont.testtask.model.Entities.Contact;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;


@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        DAO dao = new ImDAO();
        dao.init();
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        for (Object con: ((ImDAO) dao).test()
             ) {
            Contact c = (Contact) con;
            layout.addComponent(new Label(String.format("%s, %s, %s", c.getEmail(), c.getName(), c.getName())));
        }

        setContent(layout);
    }
}