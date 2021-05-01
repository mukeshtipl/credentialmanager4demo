package com.mylearning.credentialmanager4demo.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.credentialmanager4demo.model.Container;
import com.mylearning.credentialmanager4demo.service.IContainerService;


@RestController
@RequestMapping("/v1")
public class ContainerRestController {
	
	@Autowired
	IContainerService containerService;

	  @RequestMapping("/containers-test")
	    public @ResponseBody ResponseEntity<String> random() {
	        
	    	
	    	String message="{containers : all }";
	    	List<String> list = new ArrayList<>();
	       
	    	list.add(message);
	        return new ResponseEntity<String>(message, HttpStatus.OK);
	    }

	  @RequestMapping("/containers")
	    public @ResponseBody ResponseEntity<List<Container>> getContainers() {
	        
	    	List<Container> list = (List<Container>) containerService.findAll();
	       
	        return new ResponseEntity<List<Container>>(list, HttpStatus.OK);
	    }

}
