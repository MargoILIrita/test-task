package com.haulmont.testtask.view;

import com.haulmont.testtask.model.DAO.DAO;
import com.haulmont.testtask.model.Entities.AbstractHuman;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public abstract class AbstractHumanFormUI extends Window {
    protected AbstractHuman human = null;
    protected DAO dao = null;
    protected BeanItemContainer container;

    protected TextField surname;
    protected TextField name;
    protected TextField patronymic;

    public  AbstractHumanFormUI(AbstractHuman human, Container container) {
        super();
        this.container = (BeanItemContainer)container;
        if(human != null) this.human = human;
    }

    protected abstract FormLayout printComponent();

    protected void printName(Layout layout){
        surname = new TextField("Last Name");
        surname.setValue(human != null ? human.getLastName() : "");
        surname.setRequired(true);
        surname.addValidator(new StringLengthValidator("Enter Last Name", 1,10,false));
        layout.addComponent(surname);

        name = new TextField("First Name");
        name.setValue(human == null ? "": human.getName());
        name.setRequired(true);
        name.addValidator(new StringLengthValidator("Enter First Name", 1,10,false));
        layout.addComponent(name);

        patronymic = new TextField("Patronymic");
        patronymic.setValue(human != null ? human.getPatronymic():"");
        layout.addComponent(patronymic);
    }

    protected abstract void printButtons(Layout layout);
}
