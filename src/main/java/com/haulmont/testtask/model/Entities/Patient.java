package com.haulmont.testtask.model.Entities;

import javax.persistence.Entity;

@Entity
public class Patient  extends AbstractHuman {

    private long phone_number;

    public Patient(){}

    public Patient(String name, String surname, String patronomyc , long phone_number) {
        this.phone_number = phone_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronomyc;
    }

    public Patient(long id, String name, String surname, String patronomyc , long phone_number) {
        this.phone_number = phone_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronomyc;
        this.id = id;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
}
