package com.cg.blog.application.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(generator = "postSeq")
	@SequenceGenerator(name = "postSeq", sequenceName = "post_seq", allocationSize = 1)
	private int postId;

	@Column(name = "title")
	@NotNull
	private String title;

	@Lob
	@NotNull
	@Column(name = "description")
	private String description;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blogger_id", referencedColumnName = "id")
	private Blogger createdBy;

	@Column(name = "content")
	private PostType content;

	@JsonManagedReference(value = "award-post")
	@Column(name = "awards_recieved")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private List<Award> awardsReceived = new ArrayList<>();

	@CreationTimestamp
	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;

	@JsonManagedReference(value = "post-back-reference")
	@Column(name = "comments")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
	private List<Comment> comments;

	@Column(name = "votes")
	private int votes;

	@Column(name = "not_safe_for_work")
	private boolean notSafeForWork;

	@Column(name = "spoiler")
	private boolean spoiler;

	@Column(name = "original_content")
	private boolean originalContent;

	@Column(name = "flair")
	private String flair;

	@JsonBackReference(value = "community-post")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "community_id", referencedColumnName = "communityId")
	private Community community;

	public Post() {
		super();
	}

	public Post(int postId, @NotNull String title, @NotNull String description, Blogger createdBy, PostType content,
			List<Award> awardsReceived, LocalDateTime createdDateTime, List<Comment> comments, int votes,
			boolean notSafeForWork, boolean spoiler, boolean originalContent, String flair, Community community) {
		super();
		this.postId = postId;
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.content = content;
		this.awardsReceived = awardsReceived;
		this.createdDateTime = createdDateTime;
		this.comments = comments;
		this.votes = votes;
		this.notSafeForWork = notSafeForWork;
		this.spoiler = spoiler;
		this.originalContent = originalContent;
		this.flair = flair;
		this.community = community;
	}

	public Post(@NotNull String title, @NotNull String description, Blogger createdBy, PostType content,
			List<Award> awardsReceived, LocalDateTime createdDateTime, List<Comment> comments, int votes,
			boolean notSafeForWork, boolean spoiler, boolean originalContent, String flair, Community community) {
		super();
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.content = content;
		this.awardsReceived = awardsReceived;
		this.createdDateTime = createdDateTime;
		this.comments = comments;
		this.votes = votes;
		this.notSafeForWork = notSafeForWork;
		this.spoiler = spoiler;
		this.originalContent = originalContent;
		this.flair = flair;
		this.community = community;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Blogger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Blogger createdBy) {
		this.createdBy = createdBy;
	}

	public PostType getContent() {
		return content;
	}

	public void setContent(PostType content) {
		this.content = content;
	}

	public List<Award> getAwardsReceived() {
		return awardsReceived;
	}

	public void setAwardsReceived(List<Award> awardsReceived) {
		this.awardsReceived = awardsReceived;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public boolean isNotSafeForWork() {
		return notSafeForWork;
	}

	public void setNotSafeForWork(boolean notSafeForWork) {
		this.notSafeForWork = notSafeForWork;
	}

	public boolean isSpoiler() {
		return spoiler;
	}

	public void setSpoiler(boolean spoiler) {
		this.spoiler = spoiler;
	}

	public boolean isOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(boolean originalContent) {
		this.originalContent = originalContent;
	}

	public String getFlair() {
		return flair;
	}

	public void setFlair(String flair) {
		this.flair = flair;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Post otherPost = (Post)obj;
		return (this.postId == otherPost.postId);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", description=" + description + ", createdBy="
				+ createdBy + ", content=" + content + ", awardsReceived=" + awardsReceived + ", createdDateTime="
				+ createdDateTime + ", comments=" + comments + ", votes=" + votes + ", notSafeForWork=" + notSafeForWork
				+ ", spoiler=" + spoiler + ", originalContent=" + originalContent + ", flair=" + flair + ", community="
				+ community + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}