package com.api.gobooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class GobookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GobookingApplication.class, args);
	}

}
