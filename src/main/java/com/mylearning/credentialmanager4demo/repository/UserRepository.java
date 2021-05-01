package com.mylearning.credentialmanager4demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.mylearning.credentialmanager4demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>,QueryByExampleExecutor<User> {

	

}
