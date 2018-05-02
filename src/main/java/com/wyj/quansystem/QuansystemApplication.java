package com.wyj.quansystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableCaching
@MapperScan("com.wyj.quansystem.dao")
public class QuansystemApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(QuansystemApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(QuansystemApplication.class);
	}*/

}



// Ctrl-Alt-B 查看实现类