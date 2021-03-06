package com.cg.blog.application.entities;

import java.util.Arrays;

import com.cg.blog.application.exceptions.InvalidVoteException;

public enum VoteType {
	UPVOTE(1), DOWNVOTE(-1),;

	private int direction;

	VoteType(int direction) {
		this.direction = direction;
	}

	public Integer getDirection() {
		return direction;
	}

	public static VoteType lookup(Integer direction) {
		return Arrays.stream(VoteType.values()).filter(value -> value.getDirection().equals(direction)).findAny()
				.orElseThrow(() -> new InvalidVoteException("Invalid Vote"));
	}
}
