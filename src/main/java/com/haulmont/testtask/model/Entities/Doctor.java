package com.haulmont.testtask.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Doctor extends AbstractHuman implements DTO {
    @Column(nullable = false)
    private String specialization;

    public Doctor() {
    }

    public Doctor(String name, String surname, String patronomyc , String specialization) {
        this.specialization = specialization;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronomyc;
    }

    public Doctor(long id, String name, String surname, String patronomyc , String specialization) {
        this.specialization = specialization;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronomyc;
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                       "specialization='" + specialization + '\'' +
                       ", id=" + id +
                       ", name='" + name + '\'' +
                       ", surname='" + surname + '\'' +
                       ", patronymic='" + patronymic + '\'' +
                       '}';
    }
}
