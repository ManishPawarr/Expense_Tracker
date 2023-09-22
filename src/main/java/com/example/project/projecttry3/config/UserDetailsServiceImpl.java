package com.example.project.projecttry3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.project.projecttry3.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		com.example.project.projecttry3.model.UserDetails user = userRepo.findByEmail(email);
		
		if(user != null) {
			return new CustomUserDetails(user);
		}
		
		throw new UsernameNotFoundException("User not available!");
	} 

}
