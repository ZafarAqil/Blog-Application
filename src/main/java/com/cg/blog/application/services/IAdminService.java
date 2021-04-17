package com.cg.blog.application.services;

import javax.validation.Valid;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.AdminNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;

public interface IAdminService {

	public Admin adminSignUp(Admin admin);

	Community addCommunity(@Valid Community community, int adminId) throws AdminNotFoundException;

	void deleteCommunity(int communityId, int adminId) throws AdminNotFoundException, CommunityNotFoundException;

}
