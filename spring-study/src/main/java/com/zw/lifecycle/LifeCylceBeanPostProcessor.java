package com.zw.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LifeCylceBeanPostProcessor implements BeanPostProcessor {

    public LifeCylceBeanPostProcessor() {
        super();
        System.out.println("BeanPostProcessor -> Bean后置处理器创建完成！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
		if (arg0 instanceof Person) {
			System.out
					.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！ -> 在afterPropertiesSet和init-method之前执行");
		}
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
    	if (arg0 instanceof Person) {
			System.out
					.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！ -> 在afterPropertiesSet和init-method之后执行");
		}
        return arg0;
    }
}