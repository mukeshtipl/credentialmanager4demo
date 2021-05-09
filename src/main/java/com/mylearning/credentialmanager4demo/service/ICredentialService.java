package com.mylearning.credentialmanager4demo.service;

import java.util.List;
import java.util.Optional;

import com.mylearning.credentialmanager4demo.model.Credential;

public interface ICredentialService {
	List<Credential> findAll();

	List<Credential> findByUsername(String username);

	Optional<Credential> findById(Integer id);

	Optional<Credential> updatePassword(Integer id, String encryptedPassword);
}
