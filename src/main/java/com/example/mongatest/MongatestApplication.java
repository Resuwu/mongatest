package com.example.mongatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongatestApplication.class, args);
	}

}
