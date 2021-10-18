package com.autoservicio.reactivestore.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.autoservicio.reactivestore.repositories.RoleRepository;
import com.autoservicio.reactivestore.repositories.UserRepository;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfig {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		return	http.authorizeExchange(exchanges ->
            	exchanges.anyExchange().authenticated()
            	).httpBasic().and().build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ReactiveAuthenticationManager  authenticationManager() {
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());

        authenticationManager.setPasswordEncoder(passwordEncoder());
        
        return authenticationManager;
	}
	
	@Bean
	public ReactiveUserDetailsService  userDetailsService() {
	    return (username) -> userRepository.findByUsername(username).flatMap(
				user->Mono.just(user).zipWith(
						roleRepository.findAllById(Arrays.asList(user.getRoles()))
							.map(role->new SimpleGrantedAuthority(role.getName())).collectList()
						,(u,ga)->{
							u.setGrantedAuthority(ga);
							return u;
		}));
	}
}
