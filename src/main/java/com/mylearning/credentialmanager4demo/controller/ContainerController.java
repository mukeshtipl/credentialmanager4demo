/**
 * 
 */
package com.mylearning.credentialmanager4demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mylearning.credentialmanager4demo.model.Container;
import com.mylearning.credentialmanager4demo.service.IContainerService;

/**
 * @author Mukesh Gupta
 *
 */

@Controller
public class ContainerController {

	@Autowired
	IContainerService containerService;

	@GetMapping("/containers")
    public ModelAndView showContainers() {

        List<Container> containers = containerService.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("containers", containers);

        return new ModelAndView("containers", params);
    }
}
