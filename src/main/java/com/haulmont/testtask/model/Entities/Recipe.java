package com.haulmont.testtask.model.Entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Recipe implements DTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String description;

    private Date date;

    private long validity;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Recipe() {
    }

    public Recipe(Doctor doctor, Patient patient, String description, Date date, long validity, Priority priority) {
        this.doctor = doctor;
        this.patient = patient;
        this.description = description;
        this.date = date;
        this.validity = validity;
        this.priority = priority;
    }

    public Recipe(long id, Doctor doctor, Patient patient, String description, Date date, long validity, Priority priority) {
        this.doctor = doctor;
        this.patient = patient;
        this.description = description;
        this.date = date;
        this.validity = validity;
        this.priority = priority;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                       "id=" + id +
                       ", doctor=" + doctor +
                       ", patient=" + patient +
                       ", description='" + description + '\'' +
                       ", date=" + date +
                       ", validity=" + validity +
                       ", priority=" + priority +
                       '}';
    }
}
