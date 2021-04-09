package com.cg.blog.application.entities;

public class Moderator extends Blogger {

	public boolean moderatesPostsAndComments() {
		return false;
	}

}
