package com.mylearning.credentialmanager4demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.repository.CredentialRepository;

@Service
public class CredentialService implements ICredentialService {

	@Autowired
	private CredentialRepository credentialRepository;

	public List<Credential> findAll() {

		return (List<Credential>) credentialRepository.findAll();

	}

	@Override
	public List<Credential> findByUsername(String username) {
		return credentialRepository.findByUsername(username);
	}

	@Override
	public Optional<Credential> findById(Integer id) {
		return credentialRepository.findById(id);
	}

	public Optional<Credential> updatePassword(Integer id, String encryptedPassword) {

		Credential credential = credentialRepository.findById(id).get();
		// Credential credential =
		// credentialRepository.findById(id).orElseThrow(NoSuchElementException::new);
		if (credential != null) {
			credential.setEncryptedPassword(encryptedPassword);
			return Optional.of(credentialRepository.save(credential));
		} else {
			return Optional.of(null);
		}
	}

}
