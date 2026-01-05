package com.example.issue_tracker_backend.service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issue_tracker_backend.model.*;
import com.example.issue_tracker_backend.repository.IssueRepository;
import com.example.issue_tracker_backend.repository.ProjectRepository;
import com.example.issue_tracker_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class IssueService {
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Issue saveIssue(Issue incomingIssue) {
		
		//User related code
		User userObject = incomingIssue.getAssignedTo();
		if(userObject.getId()!=null) {
			System.out.println(" userObjectReq.getId() != null ");
			Optional<User> userResult = userRepository.findById(userObject.getId());
			if(userResult.isPresent()) {
				incomingIssue.setAssignedTo(userResult.get()); //FK relationship in issues table for user id
			}else {
				throw new RuntimeException("User not Found "+userObject.getId());
			}
		}
			
		//Project related code
		Project projectObject = incomingIssue.getProject();
		if(projectObject.getId()!=null) {
			System.out.println(" projectObject.getId() != null ");
			Optional<Project> projectResult = projectRepository.findById(projectObject.getId());
			if(projectResult.isPresent()) {
				incomingIssue.setProject(projectResult.get()); //FK relationship in issues table for project id
			}else {
				throw new RuntimeException("Project not Found "+projectObject.getId());
			}
		}
		
		return issueRepository.save(incomingIssue);
		
	}

	public Issue getIssueById(Long id) {
		// TODO Auto-generated method stub
		
		Optional<Issue> issueResult = issueRepository.findById(id);
		if(issueResult.isPresent()) {
			return issueResult.get();
		} /*
			 * else { throw new RuntimeException("Issue not found : "+id); }
			 */
		return null;
	}

	@Transactional
	public  Issue assignIssue(Long id, Long userId) {
		// TODO Auto-generated method stub
		
		Issue issue = getIssueById(id);
		if(issue != null) {
			User existingUser = getUserById(userId);
			if(existingUser != null)
				issue.setAssignedTo(existingUser);
				else {
					
				}
		}
		return issue;
	}

	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		
		Optional<User> userResult = userRepository.findById(id);
		if(userResult.isPresent()) {
			return userResult.get();
		} /*
			 * else { throw new RuntimeException("Issue not found : "+id); }
			 */
		return null;
	}

	@Transactional
	public Issue updateStatusForIssue(Long id, IssueStatusRequestDTO status) {
		// TODO Auto-generated method stub
		
		Issue issue = getIssueById(status.getIssueId());
		if(issue != null) {
			issue.setStatus(status.getStatus());
		}
		return issue;
	}
}
