package com.harman.healthcare;

import com.harman.healthcare.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//spring boot application
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.harman.healthcare"})
public class HealthCareSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareSpringBoot.class, args);
	}
}
