package com.graht.aichat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.graht.aichat")
public class AiChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiChatApplication.class, args);
		System.out.println("Started AiChatApplication ...");
	}

}
