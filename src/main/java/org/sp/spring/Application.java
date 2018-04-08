package org.sp.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static Class<Application> clazz = Application.class;
	
	public static void main(String args[]) {
		SpringApplication.run(clazz, args);
	
	}


}
