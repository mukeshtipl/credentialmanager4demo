package com.mylearning.credentialmanager4demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * https://zetcode.com/all/#springboot
 */
@SpringBootApplication
public class Credentialmanager4demoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Credentialmanager4demoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Credentialmanager4demoApplication.class, args);
	}

}
