package com.haulmont.testtask.model.Entities;

import java.math.BigInteger;

public class Patient extends AbstractHuman {
    private BigInteger phone_number;

    public BigInteger getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(BigInteger phone_number) {
        this.phone_number = phone_number;
    }
}
