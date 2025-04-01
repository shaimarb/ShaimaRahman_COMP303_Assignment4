package com.spring.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer

@SpringBootApplication
public class Assign4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assign4EurekaServerApplication.class, args);
	}

}
