package com.ghada.companymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class CompanymicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymicroserviceApplication.class, args);
	}

}
