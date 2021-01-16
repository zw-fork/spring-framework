package com.zw.lifecycle;

import com.zw.processor.CustomBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zw
 * @date 2020/5/18
 */
public class LifeCycleTest {

	protected final static Log logger = LogFactory.getLog(LifeCycleTest.class.getClass());

	public static void main(String[] args) {
		System.out.println("..................现在开始初始化容器..................");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.zw.lifecycle");
		System.out.println();
		System.out.println("..................容器初始化成功..................");

		Person person = context.getBean(Person.class);
		System.out.println("person bean: " + person);
		System.out.println("person BeanFactory2: " + person.getBeanFactory2());

		PersonController personController = context.getBean(PersonController.class);
		System.out.println(personController.hello());

		System.out.println();
		System.out.println("..................现在开始关闭容器！..................");
		context.registerShutdownHook();

		System.out.println("################ 实例化、初始化bean完成");
		System.out.println("****************下面输出结果");
	//	System.out.println("描述：" + bean.getDesc());
	//	System.out.println("备注：" + bean.getRemark());

	}
}
