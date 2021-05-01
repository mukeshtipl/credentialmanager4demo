package com.mylearning.credentialmanager4demo.service;

import java.util.List;

import com.mylearning.credentialmanager4demo.model.User;

public interface IUserService {

	Iterable<User> findAll();
	User save(User user);
	//List<User> findByEmail(String email);
	//List<User> findByUsername(String username);

}
