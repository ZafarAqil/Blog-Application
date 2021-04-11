package com.cg.blog.application.entities;




import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
    @ElementCollection
    @CollectionTable(name = "community_postRulesAllowed", joinColumns = @JoinColumn(name = "communityId"))
    @Column(name = "post_rules_allowed")
	private List<String> postRulesAllowed;
    
    @ElementCollection
    @CollectionTable(name = "comm_RulesDisallowed", joinColumns = @JoinColumn(name = "communityId"))
	@Column(name = "post_rules_disallowed")
	private List<String> postRulesDisallowed;
    
    @ElementCollection
    @CollectionTable(name = "community_banning_policy", joinColumns = @JoinColumn(name = "communityId"))
	@Column(name = "banning_policy")
	private List<String> banningPolicy;
    
    @ElementCollection
    @CollectionTable(name = "community_flairs", joinColumns = @JoinColumn(name = "communityId"))
	private List<String> flairs;
    
	@ManyToMany(mappedBy = "communities")
	Set<Blogger> bloggers;
	

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
		this.postRulesDisallowed = postRulesDisAllowed;
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
		this.postRulesDisallowed = postRulesDisAllowed;
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

	@Override
	public String toString() {
		return "Community [communityId=" + communityId + ", communityDescription=" + communityDescription
				+ ", totalMembers=" + totalMembers + ", onlineMembers=" + onlineMembers + ", image=" + image
				+ ", createdOn=" + createdOn + ", postRulesAllowed=" + postRulesAllowed + ", postRulesDisallowed="
				+ postRulesDisallowed + ", banningPolicy=" + banningPolicy + ", flairs=" + flairs + "]";
	}

}

