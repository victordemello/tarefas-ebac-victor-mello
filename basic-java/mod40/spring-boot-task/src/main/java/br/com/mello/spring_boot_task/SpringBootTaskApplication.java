package br.com.mello.spring_boot_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTaskApplication {

	public static void main(String[] args) {
		System.out.println("the application is running...");
		SpringApplication.run(SpringBootTaskApplication.class, args);
	}

}
