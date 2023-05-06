package com.example.Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
