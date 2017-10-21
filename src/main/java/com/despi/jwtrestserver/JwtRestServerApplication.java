package com.despi.jwtrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JwtRestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtRestServerApplication.class, args);
	}
}
