package com.example.issue_tracker_backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.issue_tracker_backend.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
