package com.autoservicio.reactivestore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.autoservicio.reactivestore.converter.BsonTimestampConverter;
import com.autoservicio.reactivestore.converter.ObjectIdConverter;
import com.autoservicio.reactivestore.repositories.ProductRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = ProductRepository.class)
public class MongoConfig extends AbstractReactiveMongoConfiguration{

	private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
	
	final String host="replace-value";
	final String port="replace-value";
	final String database="replace-value";
	
	@Override
	protected MongoClient createReactiveMongoClient(MongoClientSettings settings) {
		final ConnectionString connectionString = new ConnectionString("mongodb://"+host+":"+port+"/"+database);

		return MongoClients.create(MongoClientSettings.builder().applyConnectionString(connectionString).build());
	}
	
	@Override
	protected String getDatabaseName() {
		return database;
	}
	
	 @Override
	 public MongoCustomConversions customConversions() {
	        converters.add(new BsonTimestampConverter());
	        converters.add(new ObjectIdConverter());
	        return new MongoCustomConversions(converters);
	 }
}
