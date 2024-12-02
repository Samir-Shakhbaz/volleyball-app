package com.sash.volleyballApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VolleyballAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolleyballAppApplication.class, args);
	}

}
