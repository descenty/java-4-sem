package com.mirea.practice18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties(RsaProperties.class)
public class Practice18Application {

	public static void main(String[] args) {
		SpringApplication.run(Practice18Application.class, args);
	}

}
