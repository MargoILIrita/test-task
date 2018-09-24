package com.haulmont.testtask.model.Entities;

public enum Validity {
    DAY_10, MONTH_1, MONTH_2, MONTH_3, YEAR, FOREVER;

    @Override
    public String toString() {
        switch (this){
            case YEAR:
                return "Year";
            case FOREVER:
                return "Forever";
            case MONTH_3:
                return "3 months";
            case MONTH_2:
                return "2 months";
            case MONTH_1:
                return "Month";
            case DAY_10:
                return "10 days";
                default:
                    return "Validity";

        }
    }
}
