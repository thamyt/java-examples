package com.example.jpa_hibernate_springdata_console.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;

import org.springframework.util.StringUtils;

public class StringListConverter implements AttributeConverter<List<String>, String> {
	private final String DELIMITER = ",";
	
	@Override
	public String convertToDatabaseColumn(List<String> stringList) {
		// TODO Auto-generated method stub
		if (stringList == null) {
            return new String();
        }
        return String.join(DELIMITER, stringList);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		//return Arrays.asList(dbData.split(DELIMITER));
		if( dbData == null ) {
			return new ArrayList<String>();
		}
		else {				
			return Arrays.asList(StringUtils.tokenizeToStringArray(dbData, DELIMITER));
		}
	}
}
