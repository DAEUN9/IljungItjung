package com.iljungitjung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableWebMvc
public class IljungitjungApplication {

	public static void main(String[] args){

		SpringApplication.run(IljungitjungApplication.class, args);
	}

}
