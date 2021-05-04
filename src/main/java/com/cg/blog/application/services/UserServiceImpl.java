package com.cg.blog.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.User;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IUserRepository;

/**
 * UserServiceImpl specific implementation of {@link IUserService}
 * <p>
 * This Service class for UserController
 * </p>
 * 
 * @author Group4
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IBloggerRepository bloggerRepository;

	@Autowired
	Blogger blogger;


}
