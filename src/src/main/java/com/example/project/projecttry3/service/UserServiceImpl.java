package com.example.project.projecttry3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.projecttry3.model.UserDetails;
import com.example.project.projecttry3.repository.SpecificRepository;
import com.example.project.projecttry3.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	 @Autowired
	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepo = userRepo;
	 }
	
	@Override
	public UserDetails createUser(UserDetails user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}


	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	
	
}
	
