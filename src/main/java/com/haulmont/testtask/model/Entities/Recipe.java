package com.haulmont.testtask.model.Entities;

import com.sun.istack.internal.NotNull;
import sun.util.calendar.BaseCalendar;

import javax.persistence.Id;

public class Recipe {
    @Id
    private long id;

    @NotNull
    private long doctor;

    @NotNull
    private long patient;

    private String description;

    private BaseCalendar.Date date;

    private long validity;

    private Priority priority;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long doctor) {
        this.doctor = doctor;
    }

    public long getPatient() {
        return patient;
    }

    public void setPatient(long patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaseCalendar.Date getDate() {
        return date;
    }

    public void setDate(BaseCalendar.Date date) {
        this.date = date;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
