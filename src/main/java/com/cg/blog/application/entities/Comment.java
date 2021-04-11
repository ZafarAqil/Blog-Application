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

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;

	@Column(name = "comment_description")
	private String commentDescription;

	@Column(name = "votes")
	private int votes;

	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "blogger_id", referencedColumnName = "user_id")
	private Blogger blogger;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "post_id", referencedColumnName = "postId")
	private Post post;

	@Column(name = "vote_up")
	private boolean voteUp;

	// constructors

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentId, String commentDescription, int votes, Blogger blogger, Post post, boolean voteUp) {
		super();
		this.commentId = commentId;
		this.commentDescription = commentDescription;
		this.votes = votes;
		this.blogger = blogger;
		this.post = post;
		this.voteUp = voteUp;
	}

	public Comment(String commentDescription, int votes, Blogger blogger, Post post, boolean voteUp) {
		super();
		this.commentDescription = commentDescription;
		this.votes = votes;
		this.blogger = blogger;
		this.post = post;
		this.voteUp = voteUp;
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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
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

	public boolean isVoteUp() {
		return voteUp;
	}

	public void setVoteUp(boolean voteUp) {
		this.voteUp = voteUp;
	}

	//toString
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentDescription=" + commentDescription + ", votes=" + votes
				+ ", blogger=" + blogger + ", post=" + post + ", voteUp=" + voteUp + "]";
	}

}
