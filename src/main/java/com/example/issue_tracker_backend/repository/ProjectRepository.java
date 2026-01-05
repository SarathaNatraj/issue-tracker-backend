package com.example.issue_tracker_backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.issue_tracker_backend.model.*;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	
	List<Issue> findAllIssueById(Long id);

}
