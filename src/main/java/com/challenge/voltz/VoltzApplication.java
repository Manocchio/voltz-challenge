package com.challenge.voltz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class VoltzApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoltzApplication.class, args);
	}

}
