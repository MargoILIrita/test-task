package com.haulmont.testtask.model.Entities;

import javax.persistence.Entity;

@Entity
public class Patient  extends AbstractHuman implements DTO {

    private String phone_number;
    private static String PHONE_FORMAT = "7 %s %s";

    public Patient(){}

    public Patient(String name, String surname, String patronomyc , String phone_number) {
        this.phone_number = String.format(PHONE_FORMAT, phone_number.substring(2,5), phone_number.substring(6));
        this.name = name;
        this.lastName = surname;
        this.patronymic = patronomyc;
    }

    public Patient(long id, String name, String surname, String patronomyc , String phone_number) {
        this.phone_number = String.format(PHONE_FORMAT, phone_number.substring(2,5), phone_number.substring(6));
        this.name = name;
        this.lastName = surname;
        this.patronymic = patronomyc;
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = String.format(PHONE_FORMAT, phone_number.substring(2,5), phone_number.substring(6));;
    }

    @Override
    public String toString() {
        return "Patient{" +
                       "phone_number=" + phone_number +
                       ", id=" + id +
                       ", name='" + name + '\'' +
                       ", surname='" + lastName + '\'' +
                       ", patronymic='" + patronymic + '\'' +
                       '}';
    }
}
