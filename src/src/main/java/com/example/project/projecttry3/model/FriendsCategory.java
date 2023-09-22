package com.example.project.projecttry3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "friendsCategory")
public class FriendsCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fId;
	
	private String name;
	
	private String description;
	
	private Long amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastCreated;
	
	@PrePersist
	private void onCreate() {
		lastCreated = new Date();
	}
	
	private String email;

	@ManyToOne
	private UserDetails user;

	public FriendsCategory() {
		super();
	}

	public FriendsCategory(Long fId, String name, String description, Long amount, Date lastCreated, String email,
			UserDetails user) {
		super();
		this.fId = fId;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.lastCreated = lastCreated;
		this.user = user;
		this.email = email;
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getLastCreated() {
		return lastCreated;
	}

	public void setLastCreated(Date lastCreated) {
		this.lastCreated = lastCreated;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
