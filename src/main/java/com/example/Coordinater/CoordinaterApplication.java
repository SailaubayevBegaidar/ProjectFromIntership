package com.example.Coordinater;

import com.example.Coordinater.run.Location;
import com.example.Coordinater.run.TasksForEngineers;
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
			TasksForEngineers tasks = new TasksForEngineers(1,"build engine", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), Location.INDOOR);
			log.info("Tasks for Engineers are: " + tasks);
		};
	}

}
