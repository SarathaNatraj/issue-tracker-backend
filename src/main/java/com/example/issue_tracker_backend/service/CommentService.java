package com.example.issue_tracker_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issue_tracker_backend.model.*;
import com.example.issue_tracker_backend.repository.CommentRepository;
import com.example.issue_tracker_backend.repository.IssueRepository;
import com.example.issue_tracker_backend.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

	public Comment saveComment(Comment commentIncoming) {

		// Issue related code
		Issue issueObject = commentIncoming.getIssue();
		if (issueObject.getId() != null) {
			Optional<Issue> existingIssue = issueRepository.findById(issueObject.getId());
			if (existingIssue.isPresent()) {
				commentIncoming.setIssue(existingIssue.get()); // FK relationship in the table comments for issue_id
			} else {
				throw new RuntimeException(" Issue not found : " + issueObject.getId());
			}
		}

		// User related code
		User userObject = commentIncoming.getCommentedBy();
		if (userObject.getId() != null) {
			Optional<User> existingUser = userRepository.findById(userObject.getId());
			if (existingUser.isPresent()) {
				commentIncoming.setCommentedBy(existingUser.get()); // FK relationship comments in the table for
																	// commented_by_id
			} else {
				throw new RuntimeException(" User not found : " + userObject.getId());
			}
		}

		return commentRepository.save(commentIncoming);
	}

	public List<Comment> getCommentByIssueId(Long issueId) {
		List<Comment> commentsList = commentRepository.findAllCommentsByIssueId(issueId);
		return commentsList;
	}

}
