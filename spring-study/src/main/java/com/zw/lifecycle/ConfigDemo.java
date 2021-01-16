package com.zw.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ConfigDemo {

	@Bean
  	String str() {
		System.out.println("hello word...");
		return "hello word...";
	}
}