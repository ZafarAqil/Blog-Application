package com.cg.blog.application.services;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final String ADMIN_NOT_FOUND = "Admin Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";

	private final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public Admin adminSignUp(Admin admin) {
		log.info("Admin Service -- adminSignUp()");
		return adminRepository.save(admin);
	}

	@Override
	public Community addCommunity(@Valid Community community, int adminId) throws AdminNotFoundException {
		log.info("Admin Service -- addCommunity()");
		if (!adminRepository.findById(adminId).isPresent())
			throw new AdminNotFoundException(ADMIN_NOT_FOUND);
		return communityRepository.save(community);
	}

	@Override
	public void deleteCommunity(int communityId, int adminId)
			throws AdminNotFoundException, CommunityNotFoundException {
		log.info("Admin Service -- deleteCommunity()");
		if (!adminRepository.findById(adminId).isPresent())
			throw new AdminNotFoundException(ADMIN_NOT_FOUND);
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		communityRepository.delete(community);
	}
}
