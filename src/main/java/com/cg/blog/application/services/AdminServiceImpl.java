package com.cg.blog.application.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.AdminNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.repositories.IAdminRepository;
import com.cg.blog.application.repositories.ICommunityRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository adminRepository;
	@Autowired
	ICommunityRepository communityRepository;

	@Override
	public Admin adminSignUp(Admin admin) {	
		return adminRepository.save(admin);	
	}

	@Override
	public Community addCommunity(@Valid Community community, int adminId) {
		adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
		Community createdCommunity = communityRepository.save(community);
		return createdCommunity;
	}

	@Override
	public void deleteCommunity(int communityId, int adminId) {
		adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
		Community community = communityRepository.findById(communityId).orElseThrow(() -> new CommunityNotFoundException("Community Not Found"));

		communityRepository.delete(community);
	}

}
