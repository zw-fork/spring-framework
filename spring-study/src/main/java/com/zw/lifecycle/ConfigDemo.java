package com.zw.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ConfigDemo {

	@Bean
  	String str() {
		System.out.println("创建@Configuration @Bean对象...");
		return "hello word...";
	}
}