package com.example.project.projecttry3.service;

import com.example.project.projecttry3.model.UserDetails;

public interface UserService {

	public UserDetails createUser(UserDetails user);
	
	public boolean checkEmail(String email);
	
	
	
}
