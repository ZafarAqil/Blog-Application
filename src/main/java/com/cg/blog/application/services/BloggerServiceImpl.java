package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;

/**
 * BloggerServiceImpl specific implementation of {@link IBloggerService}
 * <p>
 * This Service class for BloggerController
 * </p>
 * 
 * @author Group4
 *
 */
@Service
public class BloggerServiceImpl implements IBloggerService {

	private static final String BLOGGER_NOT_FOUND = "Blogger Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";

	@Autowired
	IBloggerRepository bloggerRepository;
	@Autowired
	ICommunityRepository communityRepository;

	private final Logger log = LoggerFactory.getLogger(BloggerServiceImpl.class);

	/**
	 * This method is used to store Blogger data into database
	 * 
	 * @param blogger data
	 * @return blogger data return from database
	 */
	@Override
	public Blogger addBlogger(Blogger blogger) {
		log.info("Blogger Service -- addBlogger()");
		return bloggerRepository.save(blogger);
	}

	/**
	 * This method is used to update Blogger data into database
	 * 
	 * @param blogger   data
	 * @param bloggerId of registered blogger
	 * @return if blogger not found then throw BloggerNotFoundException or else
	 *         return updated blogger data
	 * @throws BloggerNotFoundException
	 */

	@Override
	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws BloggerNotFoundException {
		log.info("Blogger Service -- updateBlogger()");
		if (!bloggerRepository.findById(bloggerId).isPresent())
			throw new BloggerNotFoundException(BLOGGER_NOT_FOUND);
		blogger.setId(bloggerId);
		return bloggerRepository.save(blogger);
	}

	/**
	 * This method is used to delete Blogger data from database if blogger not found
	 * then throw BloggerNotFoundException
	 * 
	 * @param bloggerId of registered blogger
	 * @throws BloggerNotFoundException
	 */

	@Override
	public void deleteBlogger(int bloggerId) throws BloggerNotFoundException {
		log.info("Blogger Service -- deleteBlogger()");
		if (!bloggerRepository.findById(bloggerId).isPresent())
			throw new BloggerNotFoundException(BLOGGER_NOT_FOUND);
		bloggerRepository.deleteById(bloggerId);
	}

	/**
	 * This method is used to get Blogger data from database by their bloggerId
	 * 
	 * @param bloggerId of registered blogger
	 * @return if blogger not found then throw BloggerNotFoundException or else
	 *         return blogger data
	 * @throws BloggerNotFoundException
	 */
	@Override
	public Blogger getBlogger(int bloggerId) throws BloggerNotFoundException {
		log.info("Blogger Service -- getBlogger()");
		return bloggerRepository.findById(bloggerId).orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
	}

	@Override
	public List<Blogger> getAllBloggers() {
		log.info("Blogger Service -- getAllBloggers()");
		return bloggerRepository.findAll();
	}

	/**
	 * This method is used to get all blogger list from database by their
	 * communityId if community not found then throw CommunityNotFoundException
	 * 
	 * @param communityId of registered community
	 * @throws CommunityNotFoundException
	 */

	@Override
	public Set<Blogger> getBloggerList(int communityId) throws CommunityNotFoundException {
		log.info("Blogger Service -- getBloggerList()");
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		return community.getBloggers();
	}

	/**
	 * This method is used to join community for blogger. if blogger not found then
	 * throw BloggerNotFoundException. if community not found then throw
	 * CommunityNotFoundException.
	 * 
	 * @param bloggerId   of registered blogger
	 * @param communityId of community
	 * @throws BloggerNotFoundException,CommunityNotFoundException
	 */

	@Override
	public void joinCommunity(int communityId, int bloggerId)
			throws CommunityNotFoundException, BloggerNotFoundException {
		log.info("Blogger Service -- joinCommunity()");
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
