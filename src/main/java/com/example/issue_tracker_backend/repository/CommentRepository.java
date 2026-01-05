package com.example.issue_tracker_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.issue_tracker_backend.model.Comment;
import com.example.issue_tracker_backend.model.CommentIssueResponseDTO;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Query(value = "select c.id, c.content, c.issue_id , c.commented_by_id " +
            "from comments c " +
            "join issues i on c.issue_id = i.id " +
            "where c.issue_id = :issueId", nativeQuery = true)
	List<Comment> findCommentsByIssueId(Long issueId);
	
	List<Comment> findAllCommentsByIssueId(Long issueId);
	
	/*
	 * @Query(value =
	 * "select c.id, c.content, c.issue_id , c.commented_by_id , i.description " +
	 * "from comments c " + "join issues i on c.issue_id = i.id " +
	 * "where c.issue_id = :issueId", nativeQuery = true)
	 *///List<CommentIssueResponseDTO> findCommentsByIssueId(Long issueId);

	
}
