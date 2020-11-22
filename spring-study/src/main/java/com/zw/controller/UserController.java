package com.zw.controller;

import com.zw.server.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class UserController implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean, BeanPostProcessor {

	private String username = "";

	@Autowired
	private UserService userService;

	public UserController() {
		System.out.println("UserController....");
	}

	public UserController(String username) {
		this.username = username;
		System.out.println("UserController...." + username);
	}

	public String getUsername() {
		return userService.getUserName();
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy...." + this.username);
	}

	@PostConstruct
	public void springPostConstruct(){
		System.out.println("@PostConstruct" + this.username);
	}

	public String test(){
		System.out.println("@PostConstruct" + this.username);
		return "ddd";
	}

	@PreDestroy
	public void springPreDestory(){
		System.out.println("@PreDestory" + this.username);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet...." + this.username);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof UserController) {
			System.out.println("postProcessBeforeInitialization..."+beanName+"=>"+bean + this.username);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof UserController) {
			System.out.println("postProcessAfterInitialization..."+beanName+"=>"+bean + this.username);
		}
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("User.setBeanFactory invoke" + beanFactory + this.username);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName = " + name + this.username);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("User.setApplicationContext invoke" + this.username);
	}

	public void init(){
		System.out.println("User's Init..." + this.username);
	}

	public void destory2(){
		System.out.println("User's Destroy..." + this.username);
	}
}
