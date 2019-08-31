package com.tng.timesheetapp;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CommonsLog
@SpringBootApplication
public class TimesheetappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetappApplication.class, args);
		log.info("Welcome to time sheet app");

	}

}
