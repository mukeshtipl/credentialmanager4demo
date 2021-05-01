package com.mylearning.credentialmanager4demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mylearning.credentialmanager4demo.model.Credential;
import com.mylearning.credentialmanager4demo.service.ICredentialService;

@Controller
public class CredentialController {
	@Autowired
	ICredentialService credentialService;

	@GetMapping("/credentials")
    public ModelAndView showCredentials() {

        List<Credential> credentials = credentialService.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("credentials", credentials);

        return new ModelAndView("credentials", params);
    }
	

}
