package com.example.issue_tracker_backend.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issue_tracker_backend.model.Customer;
import com.example.issue_tracker_backend.model.User;
import com.example.issue_tracker_backend.model.UserDTO;
import com.example.issue_tracker_backend.repository.CustomerRepository;
import com.example.issue_tracker_backend.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public User saveUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		User result = userRepository.save(user);
		return result;
	}

	public User getUserById(Long id) {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new RuntimeException("User not Found " + id);
		}
		
	}

	public Customer saveCustomer(@Valid Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}
}
