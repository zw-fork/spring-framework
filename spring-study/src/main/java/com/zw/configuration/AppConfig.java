package com.zw.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zw
 * @date 2020/4/23
 */
@Configuration
public class AppConfig {

	@Bean
	public UserDao userDao(){
		// 会被打印几次 ？？
		System.out.println("注入UserDao");
		return new UserDao();
	}

	@Bean
	public OrderDao orderDao(){
		// 在这里调用userDao()方法
		userDao();
		return new OrderDao();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}

class UserDao {

}

class OrderDao {

}

