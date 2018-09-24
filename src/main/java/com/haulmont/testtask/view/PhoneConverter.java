package com.haulmont.testtask.view;

import com.vaadin.data.util.converter.Converter;
import java.util.Locale;

import static com.haulmont.testtask.Constants.PHONE_FORMAT;

public class PhoneConverter implements Converter<String,String> {
    @Override
    public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if(value.isEmpty()) return "";
        if(value.startsWith("+")) return value.substring(3,6)+value.substring(7);
        return (value);
    }

    @Override
    public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        String pattern = "[+]7 9\\d{2} \\d{7}";
        if(value.isEmpty()) return "";
        if(value.matches(pattern)) return value;
        System.out.println("convertToPresentation " + String.format(PHONE_FORMAT, value.substring(0,3),value.substring(3)));
        return (String.format(PHONE_FORMAT, value.substring(0,3),value.substring(3)));
    }

    @Override
    public Class<String> getModelType() {
        return String.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
