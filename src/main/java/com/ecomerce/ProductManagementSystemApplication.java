package com.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = SecurityAutoConfiguration.class)
public class ProductManagementSystemApplication {

    public static void main(String[] args) {
        System.out.println("Initializing");
        SpringApplication.run(ProductManagementSystemApplication.class, args);
    }

}
