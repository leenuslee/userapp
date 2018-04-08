package com.leedemo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.leedemo.user"})
public class UserApp {
	public static void main(String[] args) {
		SpringApplication.run(UserApp.class, args);
	}
}
