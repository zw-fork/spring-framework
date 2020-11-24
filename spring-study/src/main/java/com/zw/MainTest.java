package com.zw;

import com.zw.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.zw");
		UserController user2 = applicationContext.getBean("user2", UserController.class);
		System.out.println(user2.getUsername());

	
	}

}