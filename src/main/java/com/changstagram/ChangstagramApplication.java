package com.changstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // DB연동 멈춤
@SpringBootApplication
public class ChangstagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChangstagramApplication.class, args);
	}

}
