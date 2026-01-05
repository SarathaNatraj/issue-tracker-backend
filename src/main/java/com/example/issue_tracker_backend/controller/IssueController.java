package com.example.issue_tracker_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.issue_tracker_backend.model.Comment;
import com.example.issue_tracker_backend.model.Issue;
import com.example.issue_tracker_backend.model.IssueStatusRequestDTO;
import com.example.issue_tracker_backend.service.CommentService;
import com.example.issue_tracker_backend.service.IssueService;

@RestController
@RequestMapping("/issues")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue){
		return ResponseEntity.ok(issueService.saveIssue(issue));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Issue> getIssueById(@PathVariable Long id){
		
		Issue issueResult = issueService.getIssueById(id);
		
		if(issueResult != null) {
			return ResponseEntity.ok(issueResult);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@PutMapping("/{id}/assign/{userId}")
	public ResponseEntity<Issue> updateIssueAssignUser(@PathVariable Long id,@PathVariable Long userId ){
		return ResponseEntity.ok(issueService.assignIssue(id, userId));
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<Issue> updateStatusForIssue(@PathVariable Long id, @RequestBody IssueStatusRequestDTO status){
		return ResponseEntity.ok(issueService.updateStatusForIssue(id, status));
	}
	@PostMapping("/{issueId}/comments")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
		return ResponseEntity.ok(commentService.saveComment(comment));
	}
	
	@GetMapping("/{issueId}/comments")
	public ResponseEntity<List<Comment>> getCommentById(@PathVariable Long issueId){
		List<Comment> commentResult = commentService.getCommentByIssueId(issueId);
		
		if(commentResult != null && commentResult.size()>0) {
			return ResponseEntity.ok(commentResult);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
