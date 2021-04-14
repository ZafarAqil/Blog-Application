package com.cg.blog.application.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;

	@Column(name = "comment_description")
	private String commentDescription;

//	@Column(name = "votes")
//	private int votes;

	@JsonBackReference(value = "user-back-reference")
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "blogger_id", referencedColumnName = "id")
	private Blogger blogger;

	@JsonBackReference(value = "post-back-reference")
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "post_id", referencedColumnName = "postId")
	private Post post;

	// constructors

	public Comment() {
		super();
	}

	public Comment(int commentId, String commentDescription, int votes, Blogger blogger, Post post) {
		super();
		this.commentId = commentId;
		this.commentDescription = commentDescription;
//		this.votes = votes;
		this.blogger = blogger;
		this.post = post;
	}

	public Comment(String commentDescription, int votes, Blogger blogger, Post post) {
		super();
		this.commentDescription = commentDescription;
//		this.votes = votes;
		this.blogger = blogger;
		this.post = post;
	}

	//getters and setters
	
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

//	public int getVotes() {
//		return votes;
//	}
//
//	public void setVotes(int votes) {
//		this.votes = votes;
//	}

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

	//toString
	
//	@Override
//	public String toString() {
//		return "Comment [commentId=" + commentId + ", commentDescription=" + commentDescription + ", votes=" + votes
//				+ ", blogger=" + blogger + ", post=" + post + ", voteUp=" + voteUp + "]";
//	}

}
