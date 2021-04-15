package com.cg.blog.application.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.exceptions.PostNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IPostRepository;

@Service
public class ModeratorServiceImpl implements IModeratorService {

	@Autowired
	IBloggerRepository bloggerRepository;

	@Autowired
	IPostRepository postRepository;

	@Transactional
	@Override
	public void deletePost(int moderator_id, int post_id) throws PostNotFoundException {
		Post post = postRepository.findById(post_id).orElseThrow(() -> new PostNotFoundException("Post Not Found"));

		if (!(post.getCommunity().getModeratedBy().getId() == moderator_id))
			throw new CommunityNotFoundException("Not Authorised!");

		postRepository.delete(post);

	}

}
