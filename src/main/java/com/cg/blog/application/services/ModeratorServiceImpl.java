package com.cg.blog.application.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.AuthenticationFailedException;
import com.cg.blog.application.exceptions.PostNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IPostRepository;

/**
 * ModeratorServiceImpl specific implemention of {@link IModeratorService}
 * <p>
 * This Service class for ModeratorController
 * </p>
 * 
 * @author Group4
 *
 */
@Service
public class ModeratorServiceImpl implements IModeratorService {

	private static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
	private static final String POST_NOT_FOUND = "Post Not Found";

	private final Logger log = LoggerFactory.getLogger(ModeratorServiceImpl.class);

	@Autowired
	IBloggerRepository bloggerRepository;

	@Autowired
	IPostRepository postRepository;

	/**
	 * This method is used to delete post data from database
	 * 
	 * @param moderatorId of moderator
	 * @param postId      of post
	 * @throws PostNotFoundException,AuthenticationFailedException
	 */
	@Transactional
	@Override
	public void deletePost(int moderatorId, int postId) throws PostNotFoundException, AuthenticationFailedException {
		log.info("Moderator Service -- deletePost()");
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
		// checking if moderator has access to the community
		if (post.getCommunity().getModeratedBy().getId() != moderatorId)
			throw new AuthenticationFailedException(UNAUTHORIZED_ACCESS);
		postRepository.delete(post);
	}
}
