package com.sr.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient  // Enables service discovery and allows OrderService to register with Eureka
@SpringBootApplication
public class Assign4OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assign4OrderServiceApplication.class, args);
	}

//	// Create a RestTemplate bean to be used by the OrderService Controller
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
	
	@Bean
	public WebClient.Builder webClientBuilder() {
	    return WebClient.builder();
	}

    
}
