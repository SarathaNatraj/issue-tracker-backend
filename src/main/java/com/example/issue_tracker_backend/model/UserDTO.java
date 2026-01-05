package com.example.issue_tracker_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
	
	private Long id;
	
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@NotNull(message = "email cannot be null")
	@Email(message = "Valid email is required")
	private String email;
	
	public UserDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	

}
