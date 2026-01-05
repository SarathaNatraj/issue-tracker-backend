package com.example.issue_tracker_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.issue_tracker_backend.model.Customer;
import com.example.issue_tracker_backend.model.User;
import com.example.issue_tracker_backend.model.UserDTO;
import com.example.issue_tracker_backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO user){
		return ResponseEntity.ok(userService.saveUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable Long id){
		User result = userService.getUserById(id);
		if(result != null) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping("/saveCustomer")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer){
		return ResponseEntity.ok(userService.saveCustomer(customer));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
		
	}

}
