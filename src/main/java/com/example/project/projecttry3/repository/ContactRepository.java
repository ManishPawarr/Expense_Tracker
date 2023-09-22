package com.example.project.projecttry3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.projecttry3.model.ContactMe;

@Repository
public interface ContactRepository extends JpaRepository<ContactMe, Long>{
	
}

