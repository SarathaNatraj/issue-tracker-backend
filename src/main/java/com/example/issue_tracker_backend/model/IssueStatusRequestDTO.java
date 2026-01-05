package com.example.issue_tracker_backend.model;

public class IssueStatusRequestDTO {
	
	private Long issueId;
	private IssueStatus status;
	public IssueStatusRequestDTO() {
		
	}
	public Long getIssueId() {
		return issueId;
	}
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	public IssueStatus getStatus() {
		return status;
	}
	public void setStatus(IssueStatus status) {
		this.status = status;
	}
	

}
