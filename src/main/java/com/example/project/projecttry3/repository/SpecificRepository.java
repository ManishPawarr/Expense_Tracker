package com.example.project.projecttry3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.projecttry3.model.UserSpecific;

@Repository
public interface SpecificRepository extends JpaRepository<UserSpecific, Long>{

	List<UserSpecific> findByEmail(String email);
	
	@Query("select spent from UserSpecific where email = ?1 and type=0")
	List<Long> spent(String email);
	
	@Query("select spent from UserSpecific where email = ?1 and type=1")
	List<Long> deposit(String email);
	
	
	    
	
}
