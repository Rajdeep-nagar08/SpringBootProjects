package com.example.UserDefinedAnnotations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserDefinedAnnotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDefinedAnnotationsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();

	}

}
