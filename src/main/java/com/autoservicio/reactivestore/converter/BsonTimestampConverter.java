package com.autoservicio.reactivestore.converter;

import java.util.Date;

import org.bson.BsonTimestamp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BsonTimestampConverter implements Converter<BsonTimestamp, Date> {

	@Override
	public Date convert(BsonTimestamp source) {
		return new Date(source.getTime()*1000l);
	}

}
