package com.zw;

import com.zw.controller.UserController;
import com.zw.enablexxx.EnableSpringStudy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableSpringStudy
public class MainTest {

	public static Log log= LogFactory.getLog(MainTest.class);


	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.zw");
		UserController user2 = applicationContext.getBean("user2", UserController.class);
		System.out.println(user2.getUsername());

	
	}

}
