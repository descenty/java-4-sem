package com.practice10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.practice10.Lighters.Lighter;

@SpringBootApplication
@ComponentScan("com.practice10.Lighters")
public class Practice10Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LighterConfig.class, args);
		Lighter lighter = context.getBean(args[0], Lighter.class);
		lighter.doLight();
	}

}
