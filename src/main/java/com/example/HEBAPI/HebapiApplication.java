package com.example.HEBAPI;

import com.example.HEBAPI.controller.APIController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableWebMvc
@ComponentScan(basePackageClasses = APIController.class)
public class HebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HebapiApplication.class, args);
	}

}
