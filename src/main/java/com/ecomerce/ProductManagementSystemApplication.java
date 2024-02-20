package com.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
		//(exclude = SecurityAutoConfiguration.class)
public class ProductManagementSystemApplication {

	public static void main(String[] args) {
		System.out.println("Initializing");
		SpringApplication.run(ProductManagementSystemApplication.class, args);
	}

}
