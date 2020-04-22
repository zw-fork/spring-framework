package com.zw.processor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 结果分析在bean实例化之前，首先执行BeanFactoryPostProcessor实现类的方法，
 * 然后通过调用bean的无参构造函数实例化bean，并调用set方法注入属性值。bean实例化后，执行初始化操作，
 * 调用两个初始化方法（两个初始化方法的顺序：先执行afterPropertiesSet，再执行init-method）前后，
 * 执行了BeanPostProcessor实现类的两个方法。
 */
public class PostProcessorTest {
        public static void main(String[] args) {
			ApplicationContext context = new AnnotationConfigApplicationContext("com.zw.processor");

			CustomBean bean = (CustomBean) context.getBean("customBean");
            System.out.println("################ 实例化、初始化bean完成");
            System.out.println("****************下面输出结果");
            System.out.println("描述：" + bean.getDesc());
            System.out.println("备注：" + bean.getRemark());

        }
}