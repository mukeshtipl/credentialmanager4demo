package com.mylearning.credentialmanager4demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.mylearning.credentialmanager4demo.model.Container;
import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.model.User;
import com.mylearning.credentialmanager4demo.rest.v1.CredentialRestController;
import com.mylearning.credentialmanager4demo.service.ICredentialService;

@ExtendWith(MockitoExtension.class)
public class CredentialRestControllerTests {
	// Mock should be created for service Object
	@Mock
	private ICredentialService credentialService;

	// Service Object as mock is injected in client object
	@InjectMocks
	private CredentialRestController credentialRestController;

	@Test
	public void credentialRestController_findAll_RowsCount_Test() {

		ArrayList<Credential> credList = new ArrayList<Credential>();
		User owner = new User("user1", "user1@gmail.com", "");

		Container container = new Container("Linux", owner);
		credList.add(new Credential("root", container, ""));
		credList.add(new Credential("admin", container, ""));
		/*
		 * when a client object call a method we can define the behavior for service
		 * object for that method so it dont need to connect to real data, however
		 * client object can test the other functionality
		 */
		when(credentialService.findAll()).thenReturn(credList);

		ResponseEntity<List<Credential>> result = credentialRestController.getCredentialsByUsername("");
		int rowCount = result.getBody().size();
		assertEquals(2, rowCount);

	}

	@Test
	public void credentialRestController_findByUserName_Test() {

		ArrayList<Credential> credList = new ArrayList<Credential>();
		User owner = new User("user1", "user1@gmail.com", "");
		Container container = new Container("Linux", owner);
		credList.add(new Credential("root", container, ""));
		credList.add(new Credential("admin", container, ""));
		// when(credentialService.findAll()).thenReturn(credList);

		when(credentialService.findByUsername("root")).thenReturn(credList.subList(0, 1));
		ResponseEntity<List<Credential>> result = credentialRestController.getCredentialsByUsername("root");
		int actual = result.getBody().size();
		assertEquals(1, actual);

	}

	@Test
	public void credentialRestController_updatePassword_Test() {

		User owner = new User("user1", "user1@gmail.com", "");
		Container container = new Container("Linux", owner);
		// Credential credential = new Credential(1, "root", container, "");

		Credential expectedCredential = new Credential(1, "root", container, "ENCPASSWORD");

		when(credentialService.updatePassword(1, "encPassWord")).thenReturn(Optional.of(expectedCredential));
		ResponseEntity<Optional<Credential>> result = credentialRestController.updatePassword(1,
				Map.of("encryptedPassword", "encPassWord"));
		Credential actual = result.getBody().get();
		assertEquals("ENCPASSWORD", actual.getPassword());

	}

}
