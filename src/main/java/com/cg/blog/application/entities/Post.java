package com.cg.blog.application.entities;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	
	@Column(name = "title")
	@NotNull
	private String title;
	
	@Column(name = "created_by")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private Blogger createdBy;
	
	@Column(name = "content")
	private PostType content;
	
	@Column(name = "data")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private List<Files> data;
	
	@Column(name = "awards_recieved")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private List<Award> awardsReceived;
	
	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name="comments")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private List<Comment> comments;
	
	@Column(name= "votes")
	private int votes;
	
	@Column(name= "vote_up")
	private boolean voteUp;
	
	@Column(name = "not_safe_for_work")
	private boolean notSafeForWork;
	
	@Column(name = "spoiler")
	private boolean spoiler;
	
	@Column(name = "original_content")
	private boolean originalContent;
	
	@Column(name = "flair")
	private String flair;
	
	@Column(name = "community")
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "communityId")
	private Community community;
	
	
	

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postId, String title, Blogger createdBy, PostType content, List<Files> data, List<Award> awardsReceived,
			LocalDateTime createdDateTime, List<Comment> comments, int votes, boolean voteUp, boolean notSafeForWork,
			boolean spoiler, boolean originalContent, String flair, Community community) {
		super();
		this.postId = postId;
		this.title = title;
		this.createdBy = createdBy;
		this.content = content;
		this.data = data;
		this.awardsReceived = awardsReceived;
		this.createdDateTime = createdDateTime;
		this.comments = comments;
		this.votes = votes;
		this.voteUp = voteUp;
		this.notSafeForWork = notSafeForWork;
		this.spoiler = spoiler;
		this.originalContent = originalContent;
		this.flair = flair;
		this.community = community;
	}

	public Post(String title, Blogger createdBy, PostType content, List<Files> data, List<Award> awardsReceived,
			LocalDateTime createdDateTime, List<Comment> comments, int votes, boolean voteUp, boolean notSafeForWork,
			boolean spoiler, boolean originalContent, String flair, Community community) {
		super();
		this.title = title;
		this.createdBy = createdBy;
		this.content = content;
		this.data = data;
		this.awardsReceived = awardsReceived;
		this.createdDateTime = createdDateTime;
		this.comments = comments;
		this.votes = votes;
		this.voteUp = voteUp;
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

	public List<Files> getData() {
		return data;
	}

	public void setData(List<Files> data) {
		this.data = data;
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

	public boolean isVoteUp() {
		return voteUp;
	}

	public void setVoteUp(boolean voteUp) {
		this.voteUp = voteUp;
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

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", createdBy=" + createdBy + ", content=" + content
				+ ", data=" + data + ", awardsReceived=" + awardsReceived + ", createdDateTime=" + createdDateTime
				+ ", comments=" + comments + ", votes=" + votes + ", voteUp=" + voteUp + ", notSafeForWork="
				+ notSafeForWork + ", spoiler=" + spoiler + ", originalContent=" + originalContent + ", flair=" + flair
				+ ", community=" + community + "]";
	}

}
