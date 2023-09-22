package com.example.project.projecttry3.model;


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
@Table (name= "userSpecific")
public class UserSpecific {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uId;
	
	private String description;
	
	private Integer spent;
	
	private String name;
	
	public long uniqueId;
	
	public String email;
	
	private Integer type; //deposit or spent

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date lastUpdate;
	
	@PrePersist
	private void onCreate() {
		lastUpdate = new java.util.Date();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}

	@ManyToOne	
	private UserDetails user;

	public UserSpecific(Long uId, String description, Integer spent, String name, long uniqueId) {
		super();
		this.uId = uId;
		this.description = description;
		this.spent = spent;
		this.name = name;
		this.uniqueId = uniqueId;
	}

	public UserSpecific() {
		super();
	}
	 
	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSpent() {
		return spent;
	}

	public void setSpent(Integer spent) {
		this.spent = spent;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserSpecific [uId=" + uId + ", description=" + description + ", spent=" + spent + ", name=" + name
				+ ", uniqueId=" + uniqueId + ", email=" + email + ", user=" + user + "]";
	}

	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(java.util.Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	

	
	
	
	
	
	
}
