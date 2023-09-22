package com.example.project.projecttry3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.projecttry3.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>{
	
	public boolean existsByEmail(String email);
	
	public UserDetails findByEmail(String email);

	public UserDetails findByName(String name);
	
	
}
