package com.mylearning.credentialmanager4demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mylearning.credentialmanager4demo.model.User;
import com.mylearning.credentialmanager4demo.repository.UserRepository;
import com.mylearning.credentialmanager4demo.service.IUserService;


@Controller
public class UserController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	IUserService userService;
	
	@GetMapping("/users")
	    public ModelAndView showUsers() {

	        Iterable<User> users = (Iterable<User>) userService.findAll();

	        Map<String, Object> params = new HashMap<>();
	        params.put("users", users);

	        return new ModelAndView("users", params);
	    }
	@GetMapping("/registration")
    public String showUserRegistrationForm(Model model) {

        model.addAttribute("user", new User());

        return "registration";
    }
	@PostMapping("/registration")
    public String handleRegistration(User user) {
        
		logger.info("Registering User : "+user);
        User newUser=userService.save(user);
        logger.info("Registered User : "+newUser);
        return "redirect:/users";
    }

	
}
