package com.haulmont.testtask.model.Entities;

import javax.persistence.Entity;

@Entity
public class Patient extends AbstractHuman {

    private String phone_number;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
