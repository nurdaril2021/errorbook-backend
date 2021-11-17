package com.blibli.qa.errorbook;

import com.blibli.qa.errorbook.repository.CommentRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = CommentRepository.class)
public class ErrorbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorbookApplication.class, args);
	}

}
