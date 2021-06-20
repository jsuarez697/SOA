package com.practicas.jonatan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.practicas"})
public class PracticaJonatanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaJonatanApplication.class, args);
	}

}
