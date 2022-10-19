package com.iljungitjung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class IljungitjungApplication {

	public static void main(String[] args) {

		SpringApplication.run(IljungitjungApplication.class, args);
	}

}
