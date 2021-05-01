package com.mylearning.credentialmanager4demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mylearning.credentialmanager4demo.model.User;
import com.mylearning.credentialmanager4demo.repository.UserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;


//	//@Override
	public Iterable<User> findAll() {

		Iterable<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}

}
