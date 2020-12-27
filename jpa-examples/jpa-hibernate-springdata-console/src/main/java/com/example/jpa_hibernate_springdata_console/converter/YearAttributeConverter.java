package com.example.jpa_hibernate_springdata_console.converter;

import java.time.Year;

import javax.persistence.AttributeConverter;

public class YearAttributeConverter implements AttributeConverter<Year, Short> {

	@Override
	public Short convertToDatabaseColumn(Year attribute) {
		if (attribute != null) {
            return (short) attribute.getValue();
        }
        return null;
	}

	@Override
	public Year convertToEntityAttribute(Short dbData) {
		if (dbData != null) {
            return Year.of(dbData);
        }
        return null;
	}
}
