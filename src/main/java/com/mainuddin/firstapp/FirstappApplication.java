package com.mainuddin.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstappApplication.class, args);
	}

}

/*
	---------------STEPS----------------
	1.hello controller creation
	2.securityconfigcustomization
	3.create your own authentication provider
	4.set it to security config
	5.create your own authentication filter by implementing
		*Filter
		*GenericFilterBean
		*OncePerRequestFilter
	6.

 */
