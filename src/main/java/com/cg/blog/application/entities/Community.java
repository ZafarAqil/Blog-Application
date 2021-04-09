package com.cg.blog.application.entities;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "communities")
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int communityId;
	@Column(name = "community_description")
	private String communityDescription;
	@Column(name = "total_members")
	private int totalMembers;
	@Column(name = "online_members")
	private int onlineMembers;
	@Column
	private File image;
	@Column(name = "created_on")
	private LocalDate createdOn;
	@Column(name = "post_rules_allowed")
	private List<String> postRulesAllowed;
	@Column(name = "post_rules_disllowed")
	private List<String> postRulesDisAllowed;
	@Column(name = "banning_policy")
	private List<String> banningPolicy;
	@Column
	private List<String> flairs;

	public Community() {
		super();
	}

	public Community(int communityId, String communityDescription, int totalMembers, int onlineMembers, File image,
			LocalDate createdOn, List<String> postRulesAllowed, List<String> postRulesDisAllowed,
			List<String> banningPolicy, List<String> flairs) {
		super();
		this.communityId = communityId;
		this.communityDescription = communityDescription;
		this.totalMembers = totalMembers;
		this.onlineMembers = onlineMembers;
		this.image = image;
		this.createdOn = createdOn;
		this.postRulesAllowed = postRulesAllowed;
		this.postRulesDisAllowed = postRulesDisAllowed;
		this.banningPolicy = banningPolicy;
		this.flairs = flairs;
	}

	public Community(String communityDescription, int totalMembers, int onlineMembers, File image, LocalDate createdOn,
			List<String> postRulesAllowed, List<String> postRulesDisAllowed, List<String> banningPolicy,
			List<String> flairs) {
		super();
		this.communityDescription = communityDescription;
		this.totalMembers = totalMembers;
		this.onlineMembers = onlineMembers;
		this.image = image;
		this.createdOn = createdOn;
		this.postRulesAllowed = postRulesAllowed;
		this.postRulesDisAllowed = postRulesDisAllowed;
		this.banningPolicy = banningPolicy;
		this.flairs = flairs;
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

	public int getOnlineMembers() {
		return onlineMembers;
	}

	public void setOnlineMembers(int onlineMembers) {
		this.onlineMembers = onlineMembers;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public List<String> getPostRulesAllowed() {
		return postRulesAllowed;
	}

	public void setPostRulesAllowed(List<String> postRulesAllowed) {
		this.postRulesAllowed = postRulesAllowed;
	}

	public List<String> getPostRulesDisAllowed() {
		return postRulesDisAllowed;
	}

	public void setPostRulesDisAllowed(List<String> postRulesDisAllowed) {
		this.postRulesDisAllowed = postRulesDisAllowed;
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

}
