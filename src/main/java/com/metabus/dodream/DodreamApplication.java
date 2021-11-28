package com.metabus.dodream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DodreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DodreamApplication.class, args);
	}

}
