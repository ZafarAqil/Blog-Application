package com.cg.blog.application.services;

import javax.validation.Valid;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;

public interface IAdminService {

	public Admin adminSignUp(Admin admin);

	Community addCommunity(@Valid Community community, int adminId);

	void deleteCommunity(int communityId, int adminId);

}
