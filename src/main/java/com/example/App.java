package com.example;

import java.security.Provider;
import java.security.SecureRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping("/secure-random")
	public String secureRandom() {
		SecureRandom secureRandom = new SecureRandom();
		Provider provider = secureRandom.getProvider();
		
		StringBuilder sb = new StringBuilder();
		sb.append("getProvider().getName(): ").append( provider.getName()).append("<br />");
		sb.append("getAlgorithm():").append(secureRandom.getAlgorithm()).append("<br />");
		return sb.toString();
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}

