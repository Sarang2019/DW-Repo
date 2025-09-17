package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AllMethodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllMethodsApplication.class, args);
	}

}
