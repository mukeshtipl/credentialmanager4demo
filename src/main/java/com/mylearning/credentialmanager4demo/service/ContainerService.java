package com.mylearning.credentialmanager4demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mylearning.credentialmanager4demo.model.Container;
import com.mylearning.credentialmanager4demo.model.User;
import com.mylearning.credentialmanager4demo.repository.ContainerRepository;


@Service
public class ContainerService implements IContainerService {

	  @Autowired
	   private ContainerRepository containerRepository;
	  
	 

	
	public List<Container> findAll() {
		
		return (List<Container>) containerRepository.findAll();
      
	}
	
}
