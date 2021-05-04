package com.cg.blog.application.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
@Table(name = "bloggers")
public class Blogger extends User{

	@JsonManagedReference
	@Column(name = "posts")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<Post> posts;

	@JsonManagedReference(value = "user-back-reference")
	@Column(name = "comments")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blogger")
	private List<Comment> comments;

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
	@JoinTable(name = "blogger_communities", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "communityId", referencedColumnName = "communityId"))
	private Set<Community> communities;

//	@JsonManagedReference(value = "moderator-community")
	@JsonIgnore
	@Column(name = "mod_communities")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "moderatedBy", cascade = CascadeType.ALL)
	private Set<Community> modCommunities;

	public Blogger() {
		super();
	}


//
//	public Blogger(int id, String name, String email, String password, String role, long karma, List<Post> posts,
//			List<Comment> comments, List<Award> awardsReceived, List<Award> awardsGiven, Set<Community> communities,
//			Set<Community> moderatesCommunities) {
//		super(id, name, email, password, role, karma);

//
//	}
	
	



	public Blogger(int id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Set<Role> roles, long karma, List<Post> posts,
			List<Comment> comments, List<Award> awardsReceived, List<Award> awardsGiven, Set<Community> communities,
			Set<Community> moderatesCommunities) {
		super(id, username, email, password, roles, karma);
		this.posts = posts;
		this.comments = comments;
		this.awardsReceived = awardsReceived;
		this.awardsGiven = awardsGiven;
		this.communities = communities;
		this.modCommunities = moderatesCommunities;
		}
	
	public Blogger( @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Set<Role> roles, long karma, List<Post> posts,
			List<Comment> comments, List<Award> awardsReceived, List<Award> awardsGiven, Set<Community> communities,
			Set<Community> moderatesCommunities) {
		super( username, email, password, roles, karma);
		this.posts = posts;
		this.comments = comments;
		this.awardsReceived = awardsReceived;
		this.awardsGiven = awardsGiven;
		this.communities = communities;
		this.modCommunities = moderatesCommunities;
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

	public void setCommunities(Set<Community> communities) {
		this.communities = communities;
	}

	public Set<Community> getCommunities() {
		return communities;
	}

	public Set<Community> getModCommunities() {
		return modCommunities;
	}

	public void setModCommunities(Set<Community> moderatesCommunities) {
		this.modCommunities = moderatesCommunities;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Blogger blogger = (Blogger) obj;
		return this.getId() == blogger.getId();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}


	@Override
	public String toString() {
		return "Blogger [posts=" + posts + ", comments=" + comments + ", awardsReceived=" + awardsReceived
				+ ", awardsGiven=" + awardsGiven + ", communities=" + communities + ", modCommunities=" + modCommunities
				+ "]";
	}



}
