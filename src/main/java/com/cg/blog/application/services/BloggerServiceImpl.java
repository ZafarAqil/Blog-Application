package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;

@Service
public class BloggerServiceImpl implements IBloggerService {

	private static final String BLOGGER_NOT_FOUND = "Blogger Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";

	@Autowired
	IBloggerRepository bloggerRepository;
	@Autowired
	ICommunityRepository communityRepository;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		return bloggerRepository.save(blogger);
	}

	@Override
	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws BloggerNotFoundException {
		if (!bloggerRepository.findById(bloggerId).isPresent())
			throw new BloggerNotFoundException(BLOGGER_NOT_FOUND);
		blogger.setId(bloggerId);
		return bloggerRepository.save(blogger);
	}

	@Override
	public void deleteBlogger(int bloggerId) throws BloggerNotFoundException {
		if (!bloggerRepository.findById(bloggerId).isPresent())
			throw new BloggerNotFoundException(BLOGGER_NOT_FOUND);
		bloggerRepository.deleteById(bloggerId);
	}

	@Override
	public Blogger getBlogger(int bloggerId) throws BloggerNotFoundException {
		return bloggerRepository.findById(bloggerId).orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
	}

	@Override
	public List<Blogger> getAllBloggers() {
		return bloggerRepository.findAll();
	}

	@Override
	public Set<Blogger> getBloggerList(int communityId) throws CommunityNotFoundException {
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		return community.getBloggers();
	}

	@Override
	public void joinCommunity(int communityId, int bloggerId)
			throws CommunityNotFoundException, BloggerNotFoundException {
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		community.getBloggers().add(blogger);
		blogger.getCommunities().add(community);
		community.setTotalMembers(community.getTotalMembers() + 1);
		bloggerRepository.save(blogger);
		communityRepository.save(community);
	}

}
