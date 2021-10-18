package com.autoservicio.reactivestore.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.Role;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

}
