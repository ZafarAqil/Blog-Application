package com.cg.blog.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.User;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IUserRepository;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IBloggerRepository bloggerRepository;
	
	@Autowired
	Blogger blogger;
	@Override
	public User addNewUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);	
		}


	@Override
	public User signIn(User user) {
		if(user.getPassword().equals(((User) userRepository.findByName(user.getName())).getPassword())) {
			blogger.setUserId(user.getId());
			blogger.setBloggerName(user.getName());
			bloggerRepository.save(blogger);
			return user;
		}
		return null;
		
		
	}

	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
