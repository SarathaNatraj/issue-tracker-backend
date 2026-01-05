package com.example.issue_tracker_backend.model;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Embeddable
public class Address {
	
	@NotNull(message = "streetName is required")
	private String streetName;
	
	@NotBlank(message = "city is required")
	private String city;
	
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	@NotBlank(message = "state is required")
	private String state;
	
	 public Address() {
		 
	 }

	 
	 public String getStreetName() {
		 return streetName;
	 }

	 public void setStreetName(String streetName) {
		 this.streetName = streetName;
	 }

	 public String getCity() {
		 return city;
	 }

	 public void setCity(String city) {
		 this.city = city;
	 }

		
	 
	
	
	

}
