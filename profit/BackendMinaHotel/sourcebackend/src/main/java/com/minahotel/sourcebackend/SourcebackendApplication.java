package com.minahotel.sourcebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.minahotel.sourcebackend*")
public class SourcebackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SourcebackendApplication.class);
		app.setAdditionalProfiles("dev");
		app.setAdditionalProfiles("production");
		app.run(args);
	}
}
