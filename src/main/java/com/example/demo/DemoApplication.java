package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	/**
	 * Runs the main SpringBootApplication. This will run it in WAR format. You need to build the application
	 * in war format for it to take effect.
	 * @param application the spring application
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	/**
	 * Runs the main SpringBootApplication. This will run it in JAR format.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// FOR allowing web app and restapi to run on different ports
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/products").allowedOrigins("http://localhost:8080"); }
//		}; }
}
