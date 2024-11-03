package com.weatherweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherwebApplication.class, args);
	}

}
