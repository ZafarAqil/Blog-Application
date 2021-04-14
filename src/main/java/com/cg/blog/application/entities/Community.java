package com.cg.blog.application.entities;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "communities")
public class Community {

	@Id
	@GeneratedValue(generator = "communitySeq")
	@SequenceGenerator(name = "communitySeq", sequenceName = "community_seq", allocationSize = 1)
	private int communityId;

	@Column(name = "title")
	@NotNull
	private String title;

	@Column(name = "community_description")
	@NotNull
	private String communityDescription;

	@Column(name = "total_members")
	private int totalMembers;

//	@Column(name = "online_members")
//	private int onlineMembers;

	@Column
	private File image;

	@CreationTimestamp
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "comm_post_allowed", joinColumns = @JoinColumn(name = "communityId"))
	@Column(name = "post_rules_allowed")
	private List<String> postRulesAllowed;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "comm_post_disallowed", joinColumns = @JoinColumn(name = "communityId"))
	@Column(name = "post_rules_disallowed")
	private List<String> postRulesDisallowed;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "comm_banning_policy", joinColumns = @JoinColumn(name = "communityId"))
	@Column(name = "banning_policy")
	private List<String> banningPolicy;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "community_flairs", joinColumns = @JoinColumn(name = "communityId"))
	private List<String> flairs;

	@ManyToMany(mappedBy = "communities", fetch = FetchType.LAZY)
	private Set<Blogger> bloggers;

	@JsonManagedReference(value = "community-post")
	@OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
	private List<Post> posts;

	public Community() {
		super();
	}

	public Community(@NotNull String title, @NotNull String communityDescription, int totalMembers, File image,
			LocalDateTime createdOn, List<String> postRulesAllowed, List<String> postRulesDisallowed,
			List<String> banningPolicy, List<String> flairs, Set<Blogger> bloggers, List<Post> posts) {
		super();
		this.title = title;
		this.communityDescription = communityDescription;
		this.totalMembers = totalMembers;
		this.image = image;
		this.createdOn = createdOn;
		this.postRulesAllowed = postRulesAllowed;
		this.postRulesDisallowed = postRulesDisallowed;
		this.banningPolicy = banningPolicy;
		this.flairs = flairs;
		this.bloggers = bloggers;
		this.posts = posts;
	}

	public Community(int communityId, @NotNull String title, @NotNull String communityDescription, int totalMembers,
			File image, LocalDateTime createdOn, List<String> postRulesAllowed, List<String> postRulesDisallowed,
			List<String> banningPolicy, List<String> flairs, Set<Blogger> bloggers, List<Post> posts) {
		super();
		this.communityId = communityId;
		this.title = title;
		this.communityDescription = communityDescription;
		this.totalMembers = totalMembers;
		this.image = image;
		this.createdOn = createdOn;
		this.postRulesAllowed = postRulesAllowed;
		this.postRulesDisallowed = postRulesDisallowed;
		this.banningPolicy = banningPolicy;
		this.flairs = flairs;
		this.bloggers = bloggers;
		this.posts = posts;
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public String getCommunityDescription() {
		return communityDescription;
	}

	public void setCommunityDescription(String communityDescription) {
		this.communityDescription = communityDescription;
	}

	public int getTotalMembers() {
		return totalMembers;
	}

	public void setTotalMembers(int totalMembers) {
		this.totalMembers = totalMembers;
	}

//	public int getOnlineMembers() {
//		return onlineMembers;
//	}
//
//	public void setOnlineMembers(int onlineMembers) {
//		this.onlineMembers = onlineMembers;
//	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public List<String> getPostRulesAllowed() {
		return postRulesAllowed;
	}

	public void setPostRulesAllowed(List<String> postRulesAllowed) {
		this.postRulesAllowed = postRulesAllowed;
	}

	public List<String> getPostRulesDisallowed() {
		return postRulesDisallowed;
	}

	public void setPostRulesDisallowed(List<String> postRulesDisAllowed) {
		this.postRulesDisallowed = postRulesDisAllowed;
	}

	public List<String> getBanningPolicy() {
		return banningPolicy;
	}

	public void setBanningPolicy(List<String> banningPolicy) {
		this.banningPolicy = banningPolicy;
	}

	public List<String> getFlairs() {
		return flairs;
	}

	public void setFlairs(List<String> flairs) {
		this.flairs = flairs;
	}

	public Set<Blogger> getBloggers() {
		return bloggers;
	}

	public void setBloggers(Set<Blogger> bloggers) {
		this.bloggers = bloggers;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	@Override
//	public String toString() {
//		return "Community [communityId=" + communityId + ", communityDescription=" + communityDescription
//				+ ", totalMembers=" + totalMembers + ", onlineMembers=" + onlineMembers + ", image=" + image
//				+ ", createdOn=" + createdOn + ", postRulesAllowed=" + postRulesAllowed + ", postRulesDisallowed="
//				+ postRulesDisallowed + ", banningPolicy=" + banningPolicy + ", flairs=" + flairs + "]";
//	}

}
