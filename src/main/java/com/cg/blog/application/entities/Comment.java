package com.cg.blog.application.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;

	@NotEmpty(message = "Description cannot be empty")
	@Column(name = "comment_description")
	private String commentDescription;

	@JsonBackReference(value = "user-back-reference")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blogger_id", referencedColumnName = "id")
	private Blogger blogger;

	@JsonBackReference(value = "post-back-reference")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", referencedColumnName = "postId")
	private Post post;
	
	@Column(name = "blogger_name")
	private String bloggerName;
	
	@CreationTimestamp
	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;

	
	// constructors

	public Comment() {
		super();
	}

	public Comment(int commentId, String commentDescription, Blogger blogger, Post post) {
		super();
		this.commentId = commentId;
		this.commentDescription = commentDescription;
		this.blogger = blogger;
		this.post = post;
	}

	public Comment(String commentDescription, Blogger blogger, Post post) {
		super();
		this.commentDescription = commentDescription;
		this.blogger = blogger;
		this.post = post;
	}

	// getters and setters

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getBloggerName() {
		return bloggerName;
	}

	public void setBloggerName(String bloggerName) {
		this.bloggerName = bloggerName;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Comment comment = (Comment) obj;
		return (this.getCommentId() == comment.getCommentId());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentDescription=" + commentDescription + ", blogger=" + blogger
				+ ", post=" + post + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}
