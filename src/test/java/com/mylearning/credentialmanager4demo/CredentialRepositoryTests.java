package com.mylearning.credentialmanager4demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mylearning.credentialmanager4demo.model.Container;
import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.model.User;
import com.mylearning.credentialmanager4demo.repository.CredentialRepository;
import com.mylearning.credentialmanager4demo.service.CredentialService;

@ExtendWith(MockitoExtension.class)
public class CredentialRepositoryTests {
	// Repoistory object as Mock
	@Mock
	private CredentialRepository credentialRepository;

	// Repository object as mock is injected in Service Object which is client
	// object
	@InjectMocks
	private CredentialService credentialService;

	@Test
	public void credentialService_updatePassword_Test() {

		User owner = new User("user1", "user1@gmail.com", "");
		Container container = new Container("Linux", owner);
		Credential credential = new Credential(1, "root", container, "");

		Credential expectedCredential = new Credential(1, "root", container, "ENCPASSWORD");

		// when(credentialService.findAll()).thenReturn(credList);

		when(credentialRepository.findById(1)).thenReturn(Optional.of(credential));
		when(credentialRepository.save(credential)).thenReturn(expectedCredential);
		Optional<Credential> result = credentialService.updatePassword(1, "encPassWord");
		Credential actual = result.get();
		assertEquals("ENCPASSWORD", actual.getPassword());

	}

}
