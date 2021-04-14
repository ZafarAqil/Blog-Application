package com.cg.blog.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Component
@Table(name = "bloggers")
public class Blogger extends User{ 

	@JsonManagedReference
	@Column(name = "posts")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<Post> posts;

	@JsonManagedReference(value = "user-back-reference")
	@Column(name = "comments")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blogger")
	private List<Comment> comments;

//	@Column(name = "upvoted")
//	@ManyToMany
//	@JoinTable(
//			  name = "blogger_upvoted_posts", 
//			  joinColumns = @JoinColumn(name = "id"), 
//			  inverseJoinColumns = @JoinColumn(name = "postId"))
//	private List<Post> upvoted;

//	@Column(name = "downvoted")
//	private List<Post> downvoted;

	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name = "awards_recieved")
	private List<Award> awardsReceived;

	@JsonManagedReference(value = "award-blogger")
	@Column(name = "awards_given")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blogger")
	private List<Award> awardsGiven;

	@JsonIgnore
	@Column(name = "communities")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "blogger_communities", 
			  joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "communityId", referencedColumnName = "communityId"))
	private	List<Community> communities;

	public Blogger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blogger(String name, String email, String password, String role, long karma, List<Post> posts, List<Comment> comments, List<Post> upvoted,
			List<Post> downvoted, List<Award> awardsReceived, List<Award> awardsGiven, List<Community> communities) {
		super(name, email, password, role, karma);
		this.posts = posts;
		this.comments = comments;
//		this.upvoted = upvoted;
//		this.downvoted = downvoted;
		this.awardsReceived = awardsReceived;
		this.awardsGiven = awardsGiven;
		this.communities = communities;
	}

	public Blogger(int id, String name, String email, String password, String role, long karma, List<Post> posts, List<Comment> comments, List<Post> upvoted,
			List<Post> downvoted, List<Award> awardsReceived, List<Award> awardsGiven, List<Community> communities) {
		super(id, name, email, password, role, karma);
		this.posts = posts;
		this.comments = comments;
//		this.upvoted = upvoted;
//		this.downvoted = downvoted;
		this.awardsReceived = awardsReceived;
		this.awardsGiven = awardsGiven;
		this.communities = communities;
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

//	public List<Post> getUpvoted() {
//		return upvoted;
//	}
//
//	public void setUpvoted(List<Post> upvoted) {
//		this.upvoted = upvoted;
//	}

//	public List<Post> getDownvoted() {
//		return downvoted;
//	}
//
//	public void setDownvoted(List<Post> downvoted) {
//		this.downvoted = downvoted;
//	}

	public List<Community> getCommunities() {
		return communities;
	}

	public List<Award> getAwardsReceived() {
		return awardsReceived;
	}

	public void setAwardsReceived(List<Award> awardsReceived) {
		this.awardsReceived = awardsReceived;
	}

	public List<Award> getAwardsGiven() {
		return awardsGiven;
	}

	public void setAwardsGiven(List<Award> awardsGiven) {
		this.awardsGiven = awardsGiven;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}


//	@Override
//	public String toString() {
//		return "Blogger [userId=" + userId + ", bloggerName=" + bloggerName + ", posts=" + posts + ", comments="
//				+ comments + ", upvoted=" + upvoted + ", downvoted=" + downvoted + ", awardsReceived=" + awardsReceived
//				+ ", awardsGiven=" + awardsGiven + ", communities=" + communities + ", karma=" + karma + "]";
//	}
}
