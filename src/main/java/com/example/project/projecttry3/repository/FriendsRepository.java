package com.example.project.projecttry3.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.projecttry3.model.FriendsCategory;

@Repository
public interface FriendsRepository extends JpaRepository<FriendsCategory, Long>{
	
	public List<FriendsCategory> findByEmail(String email);
	
	@Query("select f.name from FriendsCategory f where f.email = ?1")
	List<String> arr1(String email);
	
	@Query("select f.amount from FriendsCategory f where f.email = ?1")
	List<Integer> arr2(String email);
	
	@Transactional
	@Query("delete from FriendsCategory where email = ?1")
	void deleteByEmail(String email);

}
