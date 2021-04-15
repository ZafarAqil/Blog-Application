package com.cg.blog.application.services;

import com.cg.blog.application.exceptions.AuthenticationFailedException;
import com.cg.blog.application.exceptions.PostNotFoundException;

public interface IModeratorService {

	void deletePost(int moderatorId, int postId) throws PostNotFoundException, AuthenticationFailedException;

}
