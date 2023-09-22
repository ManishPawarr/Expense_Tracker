package com.example.project.projecttry3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name="UserDetails")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String email;
	
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserSpecific> getUserSpecific() {
		return userSpecific;
	}

	public void setUserSpecific(List<UserSpecific> userSpecific) {
		this.userSpecific = userSpecific;
	}
	
	public List<FriendsCategory> getfCategory() {
		return fCategory;
	}

	public void setfCategory(List<FriendsCategory> fCategory) {
		this.fCategory = fCategory;
	}



	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserSpecific> userSpecific = new ArrayList<UserSpecific>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<FriendsCategory> fCategory = new ArrayList<FriendsCategory>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<ContactMe> cMe = new ArrayList<ContactMe>();
	
}
