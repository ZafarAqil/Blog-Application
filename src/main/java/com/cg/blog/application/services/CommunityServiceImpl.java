package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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

	@Autowired
	ICommunityRepository communityRepository;
	@Autowired
	IBloggerRepository bloggerRepository;

	@Transactional
	@Override
	public Community addCommunity(Community community, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException {
		Blogger blogger = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		community.setModeratedBy(blogger);
		blogger.getModCommunities().add(community);
		Community createdCommunity = communityRepository.save(community);
		bloggerRepository.save(blogger);
		return createdCommunity;
	}

	@Override
	public Community updateCommunity(Community community, int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException {
		Community oldCommunity = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException("Community Not Found"));
		Blogger moderator = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		// checking if blogger is a moderator of this community
		if (!oldCommunity.getModeratedBy().equals(moderator))
			throw new AuthenticationFailedException("Unauthorized Access");

		community.setCommunityId(communityId);
		community.setModeratedBy(moderator);
		return communityRepository.save(community);
	}

	@Transactional
	@Override
	public void deleteCommunity(int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException {
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException("Community Not Found"));
		Blogger moderator = bloggerRepository.findById(moderatorId)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		// checking if blogger is a moderator of this community
		if (!community.getModeratedBy().equals(moderator))
			throw new AuthenticationFailedException("Unauthorized Access");

		communityRepository.deleteById(communityId);
	}

	@Override
	public List<Community> listAllCommunitiesBySearchString(String searchString) {
		return communityRepository.findByTitleContainsIgnoreCase(searchString);
	}

	@Override
	public Set<Community> listAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException {
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		return blogger.getCommunities();
	}

	public List<String> listAllCommunities() {
		return communityRepository.findAll().stream().map(community -> community.getTitle()).collect(Collectors.toList());
	}
}