package com.autoservicio.reactivestore.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Document(collection="user")
@Data
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String username;
	private String password;
	private String fullname;
	private Boolean enabled;
	private String []roles;
	private List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthority;
	}
	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}
	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
