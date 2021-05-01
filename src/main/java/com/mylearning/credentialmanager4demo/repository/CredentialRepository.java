package com.mylearning.credentialmanager4demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mylearning.credentialmanager4demo.model.Credential;

public interface CredentialRepository extends CrudRepository<Credential, Integer> {
	
	List<Credential> findByUsername(String username);

}
