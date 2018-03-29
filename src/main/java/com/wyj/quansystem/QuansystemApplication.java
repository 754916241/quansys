package com.wyj.quansystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class QuansystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(QuansystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(QuansystemApplication.class);
	}
}



// Ctrl-Alt-B 查看实现类