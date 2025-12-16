package com.carrental.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CarRentalApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(CarRentalApiApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CarRentalApiApplication.class, args);
		Environment env = context.getEnvironment();

		String[] activeProfiles = env.getActiveProfiles();
		if (activeProfiles.length == 0) {
			logger.info("No active profiles set, using default profiles.");
		} else {
			if (logger.isInfoEnabled()) {
			        logger.info("Active profiles: {}", String.join(", ", activeProfiles));
			    }
		}
	}

}
