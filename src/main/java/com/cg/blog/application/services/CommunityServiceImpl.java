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
import com.cg.blog.application.exceptions.AuthenticationFailedException;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;

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

	@Transactional
	@Override
	public Community addCommunity(Community community, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException {
		log.info("Community Service -- addCommunity()");
		Blogger blogger = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		community.setModeratedBy(blogger);
		blogger.getModCommunities().add(community);
		Community createdCommunity = communityRepository.save(community);
		bloggerRepository.save(blogger);
		return createdCommunity;
	}

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

	@Override
	public List<Community> getAllCommunitiesBySearchString(String searchString) {
		log.info("Community Service -- getAllCommunitiesBySearchString()");
		return communityRepository.findByTitleContainsIgnoreCase(searchString);
	}

	@Override
	public Set<Community> getAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException {
		log.info("Community Service -- getAllCommunitiesByBlogger()");
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		return blogger.getCommunities();
	}

	@Override
	public List<String> getAllCommunities() {
		log.info("Community Service -- getAllCommunities()");
		return communityRepository.findAll().stream().map(Community::getTitle).collect(Collectors.toList());
	}

	@Override
	public Community getCommunity(int communityId) throws CommunityNotFoundException {
		log.info("Community Service -- getCommunity()");
		return communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
	}
}