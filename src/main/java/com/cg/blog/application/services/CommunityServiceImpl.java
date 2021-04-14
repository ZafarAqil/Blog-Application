package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
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

	@Override
	public Community addCommunity(Community community) {
		Community createdCommunity = communityRepository.save(community);
		return createdCommunity;
	}

	@Override
	public Community updateCommunity(Community community, int communityId) throws CommunityNotFoundException {
		communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException("Community Not Found"));
		community.setCommunityId(communityId);
		Community updatedCommunity = communityRepository.save(community);

		return updatedCommunity;
	}

	@Transactional
	@Override
	public void deleteCommunity(int communityId) throws CommunityNotFoundException {

		communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException("Community Not Found"));
		communityRepository.deleteById(communityId);
	}

	@Override
	public List<Community> listAllCommunities(String searchString) {
		return communityRepository.findByTitleContainsIgnoreCase(searchString);
	}

	@Override
	public Set<Community> listAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException {
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		return blogger.getCommunities();
	}

}