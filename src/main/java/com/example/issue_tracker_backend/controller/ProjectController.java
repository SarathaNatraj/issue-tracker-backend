package com.example.issue_tracker_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.issue_tracker_backend.model.Issue;
import com.example.issue_tracker_backend.model.Project;
import com.example.issue_tracker_backend.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	
	@PostMapping
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		return ResponseEntity.ok(projectService.saveProject(project));
	}
	
	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects(){
		return ResponseEntity.ok(projectService.getAllProjects());
		
	}
	@GetMapping("/{id}/issues")
	public ResponseEntity<List<Issue>> getAllIssuesProjectId(@PathVariable Long id){
		return ResponseEntity.ok(projectService.getAllIssuesProjectId(id));
		
	}
}
