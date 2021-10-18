package com.autoservicio.reactivestore.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String>{
	@Query("{'username':?0}")
	Mono<User>findByUsername(String username);
}
