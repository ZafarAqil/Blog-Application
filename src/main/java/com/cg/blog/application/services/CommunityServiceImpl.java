package com.cg.blog.application.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.ICommunityRepository;

@Service
public class CommunityServiceImpl implements ICommunityService{

	@Autowired
	ICommunityRepository communityRepository;
	
	@Override
	public Community addCommunity(Community community) {
		Community createdCommunity = communityRepository.save(community);
		return createdCommunity;
	}

	@Override
	public Community updateCommunity(Community community, int communityId) {
		communityRepository.findById(communityId).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		community.setCommunityId(communityId);
		Community updatedCommunity = communityRepository.save(community);
		
		return updatedCommunity;
	}

	@Transactional
	@Override
	public void deleteCommunity(int communityId) {

		communityRepository.findById(communityId).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		communityRepository.deleteById(communityId);
	}

	@Override
	public List<Community> listAllCommunities(String searchString) {
		
		return communityRepository.findByCommunityDescriptionContains(searchString);
	}

	@Override
	public List<Community> listAllCommunitiesByBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		return null;
	}

}
