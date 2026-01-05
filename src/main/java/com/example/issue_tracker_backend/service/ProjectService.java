package com.example.issue_tracker_backend.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issue_tracker_backend.model.Issue;
import com.example.issue_tracker_backend.model.Project;
import com.example.issue_tracker_backend.model.User;
import com.example.issue_tracker_backend.repository.ProjectRepository;
import com.example.issue_tracker_backend.repository.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Project saveProject(Project project) {
	
		User userObjectReq = project.getCreatedBy();
		if(userObjectReq.getId() != null) {
			System.out.println(" userObjectReq.getId() != null ");
			Optional<User> userResult = userRepository.findById(userObjectReq.getId());
			if(userResult.isPresent()) {
				project.setCreatedBy(userResult.get()); //FK relationship in projects table
			}else {
				throw new RuntimeException("User not Found "+userObjectReq.getId());
			}
		}
		return projectRepository.save(project);
		
		
		
	}

	
	public List<Project> getAllProjects(){
		return (List<Project>) projectRepository.findAll();
		
	}


	public List<Issue> getAllIssuesProjectId(Long id) {
		// TODO Auto-generated method stub
		return projectRepository.findAllIssueById(id);
	}
}
