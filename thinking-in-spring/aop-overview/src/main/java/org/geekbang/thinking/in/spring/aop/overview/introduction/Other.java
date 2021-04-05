package org.geekbang.thinking.in.spring.aop.overview.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class Other implements IntroductionInterceptor,IOther{

	//要织入的方法
	public void doOther(){
		System.out.println("对Some进行增强后 -> Other对象的功能。");
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if(implementsInterface(methodInvocation.getMethod().getDeclaringClass())){
			System.out.println("使用织入的方法...");
			Object obj = methodInvocation.getMethod().invoke(this, methodInvocation.getArguments());
			System.out.println("注入后...");
			return obj;
		}
		else{
			return methodInvocation.proceed();
		}
	}

	@Override
	public boolean implementsInterface(Class intf){
		boolean assignableFrom = intf.isAssignableFrom(IOther.class);
		return assignableFrom;
	}	
}