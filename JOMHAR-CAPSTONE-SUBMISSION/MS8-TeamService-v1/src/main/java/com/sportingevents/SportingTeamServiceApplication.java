package com.sportingevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SportingTeamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportingTeamServiceApplication.class, args);
	}

}
