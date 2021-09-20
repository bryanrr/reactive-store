package com.autoservicio.reactivestore.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

public class ObjectIdConverter implements Converter<ObjectId,String>{

	@Override
	public String convert(ObjectId source) {
		
		return source.toString();
	}

}
