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
@Table (name = "contact_me")
public class ContactMe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;
	
	private String name;

	private String email;
	
	private Long phone;
	
	private String description;
	
	private String user_email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastSent;
	
	@PrePersist
	private void onCreate() {
		lastSent = new Date();
	}
	
	@ManyToOne
	private UserDetails user;

	public ContactMe() {
		super();
	}

	public ContactMe(Long contactId, String name, String email, Long phone, String description, Date lastSent,
			UserDetails user, String user_email) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.lastSent = lastSent;
		this.user = user;
		this.user_email = user_email;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastSent() {
		return lastSent;
	}

	public void setLastSent(Date lastSent) {
		this.lastSent = lastSent;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
	
}
