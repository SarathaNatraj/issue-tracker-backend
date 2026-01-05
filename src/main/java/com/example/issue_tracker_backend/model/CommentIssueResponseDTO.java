package com.example.issue_tracker_backend.model;

public class CommentIssueResponseDTO {
	
	private Comment comment;
	private Issue issue;
	
	
	public CommentIssueResponseDTO() {
		
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public Issue getIssue() {
		return issue;
	}


	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	

}
