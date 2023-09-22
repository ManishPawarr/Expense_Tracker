package com.example.project.projecttry3.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.example.project.projecttry3.model.UserDetails;

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails{

	private UserDetails user; 
	
	public CustomUserDetails(UserDetails user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
