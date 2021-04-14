package com.cg.blog.application.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "votes")
@Component
public class Vote {

	@Id
	@GeneratedValue(generator = "voteSeq")
	@SequenceGenerator(name = "voteSeq", sequenceName = "vote_seq", allocationSize = 1)
	private Long voteId;
	private VoteType voteType;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", referencedColumnName = "postId")
	private Post post;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Blogger blogger;

	public Vote() {
		super();
	}

	public Vote(Long voteId, VoteType voteType, @NotNull Post post, Blogger blogger) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.post = post;
		this.blogger = blogger;
	}

	public Vote(VoteType voteType, @NotNull Post post, Blogger blogger) {
		super();
		this.voteType = voteType;
		this.post = post;
		this.blogger = blogger;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

}