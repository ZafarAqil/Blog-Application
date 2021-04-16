package com.cg.blog.application.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "awards")
public class Award {

	@Id
	@GeneratedValue(generator = "awardSeq")
	@SequenceGenerator(name = "awardSeq", sequenceName = "award_seq", allocationSize = 1)
	private int awardId;

	private AwardType awardType;

	@JsonBackReference(value = "award-blogger")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blogger_id", referencedColumnName = "id")
	private Blogger blogger;

	@JsonBackReference(value = "award-post")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", referencedColumnName = "postId")
	private Post post;

	public Award() {
		super();
	}

	public Award(int awardId, AwardType awardType, Blogger blogger, Post post) {
		super();
		this.awardId = awardId;
		this.awardType = awardType;
		this.blogger = blogger;
		this.post = post;
	}

	public Award(AwardType awardType, Blogger blogger, Post post) {
		super();
		this.awardType = awardType;
		this.blogger = blogger;
		this.post = post;
	}

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}

	public AwardType getAwardType() {
		return awardType;
	}

	public void setAwardType(AwardType awardType) {
		this.awardType = awardType;
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

	@Override
	public String toString() {
		return "Award [awardId=" + awardId + ", awardType=" + awardType + ", blogger=" + blogger + ", post=" + post
				+ "]";
	}

}
