package com.zw.lifecycle;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author qsk
 */
@Data
public class Person implements BeanFactoryAware, BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

	@Value(value="zw")
    private String name;
    private String address;
    private int phone;

    private String beanName;   //通过回调方法注入

	private BeanFactory beanFactory;  //通过回调方法注入
	@Autowired
	private BeanFactory beanFactory2;

	@Autowired
	private ResourceLoader resourceLoader;

	private ApplicationContext applicationContext;  //通过回调方法注入

	@Autowired
	private ApplicationContext applicationContext2;

	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

    static {
		System.out.println("Person进行初始化...");
	}

    public Person(String name) {
        System.out.println("【构造器】调用Person的构造器实例化: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name: " + name);
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("【注入属性】注入属性phone: " + phone);
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", name=" + name + ", phone="
                + phone + "]";
    }

	/**
	 * 这是BeanFactoryAware接口方法。注入beanFactory对象
	 * @param beanFactory owning BeanFactory (never {@code null}).
	 * The bean can immediately call methods on the factory.
	 * @throws BeansException
	 */
	@Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out
                .println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory(): " + beanFactory.getClass().getSimpleName());
        this.beanFactory = beanFactory;
    }

	/**
	 * 这是BeanNameAware接口方法。注入当前Bean的ID
	 * @param beanId
	 */
	@Override
    public void setBeanName(String beanId) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()，参数为bean的ID: " + beanId);
        this.beanName = beanId;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out
                .println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

	@PostConstruct
	void postConstruct() {
		System.out.println("调用注解的初始化===== postConstruct");
	}
	@PreDestroy
	void preDestroy() {
		System.out.println("调用注解的销毁===== destroy");
	}

	/**
	 * ApplicationContextAware接口方法。注入applicationContext
	 * @param applicationContext the ApplicationContext object to be used by this object
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("【ApplicationContextAware接口】调用ApplicationContextAware.setApplicationContext方法: " + applicationContext.getApplicationName());
		this.applicationContext = applicationContext;
	}
}