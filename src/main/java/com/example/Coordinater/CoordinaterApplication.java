package com.example.Coordinater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class CoordinaterApplication {

	private static final Logger log = LoggerFactory.getLogger(CoordinaterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoordinaterApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

		};
	}

}
