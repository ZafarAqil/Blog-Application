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
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
/**
 * AdminServiceImpl specific implementation of {@link IAdminService}
 * <p>This Service class for AdminController</p>
 * @author Group4
 *
 */
@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository adminRepository;
	@Autowired
	ICommunityRepository communityRepository;
	@Autowired
	IBloggerRepository bloggerRepository;

	private static final String ADMIN_NOT_FOUND = "Admin Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";

	private final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	/**
	 * This method is used to store Admin data into oracle database
	 * @param admin data
	 * @return admin data
	 */
	@Override
	public Admin adminSignUp(Admin admin) {
		log.info("Admin Service -- adminSignUp()");
		return adminRepository.save(admin);
	}
	/**
	 * This method is used to add community data into oracle database
	 * @param community data
	 * @return if admin not found throw custom exception or else return community data
	 * @throws AdminNotFoundException
	 */

	@Override
	public Community addCommunity(@Valid Community community, int adminId) throws AdminNotFoundException {
		log.info("Admin Service -- addCommunity()");
		if (!adminRepository.findById(adminId).isPresent())
			throw new AdminNotFoundException(ADMIN_NOT_FOUND);
		return communityRepository.save(community);
	}
	
	/**
	 * This method is used to delete community data from oracle database
	 * @param CommunityId 
	 * @param adminId
	 * @return if admin not found throw AdminNotFoundException 
	 * or if community not found throw CommunityNotFoundException
	 * @throws AdminNotFoundException,CommunityNotFoundException
	 * 
	 */

	@Override
	public void deleteCommunity(int communityId, int adminId)
			throws AdminNotFoundException, CommunityNotFoundException {
		log.info("Admin Service -- deleteCommunity()");
		if (!bloggerRepository.findById(adminId).isPresent())
			throw new AdminNotFoundException(ADMIN_NOT_FOUND);
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		communityRepository.delete(community);
	}
}
