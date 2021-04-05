package org.geekbang.thinking.in.spring.aop.overview.introduction;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Introduction{
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ProxyFactoryBeanConfig.class);
		ctx.refresh();
	    ISome some=(ISome)ctx.getBean("proxyFactoryBean");
	    some.doSome();
	    //对Some进行增强
	    ((IOther)some).doOther();
	}

	@Configuration
	static class ProxyFactoryBeanConfig {
		@Bean
		ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			//代理的接口
			proxyFactoryBean.setInterfaces(ISome.class);
			//被代理对象
			proxyFactoryBean.setTarget(some());
			//aop拦截处理类
			proxyFactoryBean.setInterceptorNames("otherAdvisor");
			return proxyFactoryBean;
		}
		@Bean
		Some some() {
			return new Some();
		}
		@Bean
		Other other() {
			return new Other();
		}
		@Bean
		DefaultIntroductionAdvisor otherAdvisor() {
			DefaultIntroductionAdvisor proxyFactoryBean = new DefaultIntroductionAdvisor(other(), IOther.class);
			return proxyFactoryBean;
		}
	}
}
