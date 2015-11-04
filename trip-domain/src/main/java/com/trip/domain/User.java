package com.trip.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -443709692450832627L;
	private String name;
	private String email;
	private String role;
	
	public enum Role{
		ADMAIN
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
