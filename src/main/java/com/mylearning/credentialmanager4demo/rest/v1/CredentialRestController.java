package com.mylearning.credentialmanager4demo.rest.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.service.ICredentialService;

@RestController
@RequestMapping("/v1")
public class CredentialRestController {
	@Autowired
	ICredentialService credentialService;

//	@RequestMapping("/credentials")
//	public @ResponseBody ResponseEntity<List<Credential>> getCredentials() {
//
//		List<Credential> list = (List<Credential>) credentialService.findAll();
//
//		return new ResponseEntity<List<Credential>>(list, HttpStatus.OK);
//	}

	@RequestMapping("/credentials")
	public @ResponseBody ResponseEntity<List<Credential>> getCredentialsByUsername(
			@RequestParam(value = "username", required = false) String username) {
		List<Credential> list;
		if (username == null || username.length() == 0)
			list = (List<Credential>) credentialService.findAll();
		else
			list = credentialService.findByUsername(username);

		return new ResponseEntity<List<Credential>>(list, HttpStatus.OK);
	}

	@RequestMapping("/credentials/{id}")
	public @ResponseBody ResponseEntity<Optional<Credential>> getCredentialsByUsername(@PathVariable Integer id) {

		Optional<Credential> credential = credentialService.findById(id);

		return new ResponseEntity<Optional<Credential>>(credential, HttpStatus.OK);
	}

	@PatchMapping("/credentials/{id}/updatePassword")
	public @ResponseBody ResponseEntity<Optional<Credential>> updatePassword(@PathVariable Integer id,
			@RequestParam(value = "encryptedPassword", required = true) String encrpytedPassword) {
		Optional<Credential> credential = credentialService.updatePassword(id, encrpytedPassword);
		return new ResponseEntity<Optional<Credential>>(credential, HttpStatus.OK);
	}

}
