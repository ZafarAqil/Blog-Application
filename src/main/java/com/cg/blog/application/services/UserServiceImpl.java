package com.cg.blog.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.User;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IBloggerRepository bloggerRepository;

	@Autowired
	Blogger blogger;

	@Override
	public User addNewUser(User user) {
		blogger = new Blogger();
		blogger.setId(user.getId());
		blogger.setEmail(user.getEmail());
		blogger.setPassword(user.getPassword());
		blogger.setName(user.getName());
		bloggerRepository.save(blogger);
		return userRepository.save(user);
	}

	@Override
	public User signIn(User user) {
		if (user.getPassword().equals(((User) userRepository.findByName(user.getName())).getPassword())) {
			user.setId(userRepository.findByName(user.getName()).getId());
			return user;
		}
		return null;

	}

	@Override
	public User signOut(User user) {
		return null;
	}

}
