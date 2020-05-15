package com.example.rest.webflux.demowebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemowebfluxApplication {

	/**
	 *curl -X GET http://localhost:8080/hola
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemowebfluxApplication.class, args);
	}

}
