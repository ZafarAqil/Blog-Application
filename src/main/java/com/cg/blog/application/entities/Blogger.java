package com.cg.blog.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Component
@Table(name = "bloggers")
public class Blogger {

	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "blogger_name")
	private String bloggerName;

//	@JsonManagedReference
	@Column(name = "posts")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<Post> posts;

//	@JsonManagedReference
	@Column(name = "comments")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blogger")
	private List<Comment> comments;

	@Column(name = "upvoted")
	@ManyToMany
	@JoinTable(
			  name = "blogger_communities", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "postId"))
	private List<Post> upvoted;

//	@Column(name = "downvoted")
//	private List<Post> downvoted;
//
//	@Column(name = "awards_recieved")
//	private Award awardsReceived;
//
//	@Column(name = "awards_given")
//	private Award awardsGiven;

	@Column(name = "communities")
	@ManyToMany
	@JoinTable(
			  name = "blogger_communities", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "communityId"))
	private	List<Community> communities;


	@Column(name = "karma")
	private int karma;

	public Blogger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blogger(int userId, String bloggerName, List<Post> posts, List<Comment> comments, List<Post> upvoted,
			List<Post> downvoted, Award awardsReceived, Award awardsGiven, List<Community> communities, int karma) {
		super();
		this.userId = userId;
		this.bloggerName = bloggerName;
		this.posts = posts;
		this.comments = comments;
		this.upvoted = upvoted;
//		this.downvoted = downvoted;
//		this.awardsReceived = awardsReceived;
//		this.awardsGiven = awardsGiven;
		this.communities = communities;
		this.karma = karma;
	}

	public Blogger(String bloggerName, List<Post> posts, List<Comment> comments, List<Post> upvoted,
			List<Post> downvoted, Award awardsReceived, Award awardsGiven, List<Community> communities, int karma) {
		super();
		this.bloggerName = bloggerName;
		this.posts = posts;
		this.comments = comments;
		this.upvoted = upvoted;
//		this.downvoted = downvoted;
//		this.awardsReceived = awardsReceived;
//		this.awardsGiven = awardsGiven;
		this.communities = communities;
		this.karma = karma;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBloggerName() {
		return bloggerName;
	}

	public void setBloggerName(String bloggerName) {
		this.bloggerName = bloggerName;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getUpvoted() {
		return upvoted;
	}

	public void setUpvoted(List<Post> upvoted) {
		this.upvoted = upvoted;
	}

//	public List<Post> getDownvoted() {
//		return downvoted;
//	}
//
//	public void setDownvoted(List<Post> downvoted) {
//		this.downvoted = downvoted;
//	}
//
//	public Award getAwardsReceived() {
//		return awardsReceived;
//	}
//
//	public void setAwardsReceived(Award awardsReceived) {
//		this.awardsReceived = awardsReceived;
//	}
//
//	public Award getAwardsGiven() {
//		return awardsGiven;
//	}
//
//	public void setAwardsGiven(Award awardsGiven) {
//		this.awardsGiven = awardsGiven;
//	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

//	@Override
//	public String toString() {
//		return "Blogger [userId=" + userId + ", bloggerName=" + bloggerName + ", posts=" + posts + ", comments="
//				+ comments + ", upvoted=" + upvoted + ", downvoted=" + downvoted + ", awardsReceived=" + awardsReceived
//				+ ", awardsGiven=" + awardsGiven + ", communities=" + communities + ", karma=" + karma + "]";
//	}
}
