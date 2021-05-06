package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.ERole;
import com.cg.blog.application.entities.Role;
import com.cg.blog.application.exceptions.AuthenticationFailedException;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IRoleRepository;

/**
 * CommunityServiceImpl specific implementation of {@link ICommunityService}
 * <p>
 * This Service class for CommunityController
 * </p>
 * 
 * @author Group4
 *
 */
@Service
public class CommunityServiceImpl implements ICommunityService {

	private static final String BLOGGER_NOT_FOUND = "Blogger Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";
	private static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";

	private final Logger log = LoggerFactory.getLogger(CommunityServiceImpl.class);

	@Autowired
	ICommunityRepository communityRepository;
	@Autowired
	IBloggerRepository bloggerRepository;
	@Autowired
	IRoleRepository roleRepository;
	/**
	 * This method is used to add community data into database
	 * 
	 * @param moderatorId of moderator
	 * @param community   data
	 * @return community data
	 * @throws BloggerNotFoundException,CommunityNotFoundException
	 */
	@Transactional
	@Override
	public Community addCommunity(Community community, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException {
		log.info("Community Service -- addCommunity()");
		Blogger blogger = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		community.setModeratedBy(blogger);
		
		
		
		Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		if(!blogger.getRoles().contains(modRole)) {
			blogger.getRoles().add(modRole);
		}
		
		
		blogger.getModCommunities().add(community);
		Community createdCommunity = communityRepository.save(community);
		bloggerRepository.save(blogger);
		return createdCommunity;
	}

	/**
	 * This method is used to update community data from database
	 * 
	 * @param communityId of community
	 * @param moderatorId of moderator
	 * @return updated community data
	 * @throws BloggerNotFoundException,CommunityNotFoundException, AuthenticationFailedException
	 */

	@Override
	public Community updateCommunity(Community community, int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException {
		log.info("Community Service -- updateCommunity()");
		Community oldCommunity = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		Blogger moderator = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		// checking if blogger is a moderator of this community
		if (oldCommunity.getModeratedBy().getId() != moderator.getId())
			throw new AuthenticationFailedException(UNAUTHORIZED_ACCESS);

		community.setCommunityId(communityId);
		community.setModeratedBy(moderator);
		return communityRepository.save(community);
	}

	/**
	 * This method is used to delete community data from database
	 * 
	 * @param communityId of community
	 * @param moderatorId of moderator
	 * @throws BloggerNotFoundException,CommunityNotFoundException, AuthenticationFailedException
	 */
	@Transactional
	@Override
	public void deleteCommunity(int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException {
		log.info("Community Service -- deleteCommunity()");
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		Blogger moderator = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		// checking if blogger is a moderator of this community
		if (!community.getModeratedBy().equals(moderator))
			throw new AuthenticationFailedException(UNAUTHORIZED_ACCESS);

		communityRepository.deleteById(communityId);
	}

	/**
	 * This method is used to get all communities by search string from database
	 * 
	 * @param searchString
	 * @return list of communities
	 */

	@Override
	public List<Community> getAllCommunitiesBySearchString(String searchString) {
		log.info("Community Service -- getAllCommunitiesBySearchString()");
		return communityRepository.findByTitleContainsIgnoreCase(searchString);
	}

	/**
	 * This method is used to get all communities by blogger from database
	 * 
	 * @param bloggerId of blogger
	 * @throws BloggerNotFoundException
	 */

	@Override
	public Set<Community> getAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException {
		log.info("Community Service -- getAllCommunitiesByBlogger()");
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		return blogger.getCommunities();
	}

	/**
	 * This method is used to get all communities from database
	 * 
	 * @return list of community title
	 */

	@Override
	public List<Community> getAllCommunities() {
		log.info("Community Service -- getAllCommunities()");
		return communityRepository.findAll();
	}

	/**
	 * This method is used to get community by communityId from database
	 * 
	 * @param communityId of community
	 * @throws CommunityNotFoundException
	 */
	@Override
	public Community getCommunity(int communityId) throws CommunityNotFoundException {
		log.info("Community Service -- getCommunity()");
		return communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
	}
}