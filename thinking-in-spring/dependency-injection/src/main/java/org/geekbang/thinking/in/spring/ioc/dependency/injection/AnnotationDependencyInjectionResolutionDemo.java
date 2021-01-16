/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Qualifier
 * @since
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {

	/**
	 * DependencyDescriptor(
	 * declaringClass=class org.geekbang.thinking.in.spring.ioc.dependency.injection.AnnotationDependencyInjectionResolutionDemo,
	 * methodName=null, parameterTypes=null, parameterIndex=0,
	 * fieldName=lazyUser,
	 * required=true,
	 * eager=true,
	 * nestingLevel=1, containingClass=null,
	 * resolvableType=org.geekbang.thinking.in.spring.ioc.overview.domain.User,
	 * typeDescriptor=@org.springframework.beans.factory.annotation.Autowired @org.springframework.context.annotation.Lazy org.geekbang.thinking.in.spring.ioc.overview.domain.User
	 * )
	 */
    @Autowired          // 依赖查找（处理） + 延迟
    @Lazy
    private User lazyUser;

    // DependencyDescriptor ->
    // 必须（required=true）
    // 实时注入（eager=true)
    // 通过类型（User.class）
    // 字段名称（"user"）
    // 是否首要（primary = true)
	/**
	 * DependencyDescriptor(
	 * declaringClass=class org.geekbang.thinking.in.spring.ioc.dependency.injection.AnnotationDependencyInjectionResolutionDemo,
	 * methodName=null, parameterTypes=null, parameterIndex=0,
	 * fieldName=user,
	 * required=true,
	 * eager=true,
	 * nestingLevel=1,
	 * containingClass=null,
	 * resolvableType=org.geekbang.thinking.in.spring.ioc.overview.domain.User,
	 * typeDescriptor=@org.springframework.beans.factory.annotation.Autowired org.geekbang.thinking.in.spring.ioc.overview.domain.User
	 * )
	 */
    @Autowired          // 依赖查找（处理）
    private User user;

	/**
	 * DependencyDescriptor(
	 * declaringClass=class org.geekbang.thinking.in.spring.ioc.dependency.injection.AnnotationDependencyInjectionResolutionDemo,
	 * methodName=null, parameterTypes=null, parameterIndex=0,
	 * fieldName=users,
	 * required=true,
	 * eager=true,
	 * nestingLevel=1,
	 * containingClass=null,
	 * resolvableType=java.util.Map<java.lang.String, org.geekbang.thinking.in.spring.ioc.overview.domain.User>,
	 * typeDescriptor=@org.springframework.beans.factory.annotation.Autowired java.util.Map<java.lang.String, org.geekbang.thinking.in.spring.ioc.overview.domain.User>
	 *  )
	 */
	@Autowired          // 集合类型依赖注入
    private Map<String, User> users; // user superUser

	/**
	 * DependencyDescriptor(
	 * declaringClass=class org.geekbang.thinking.in.spring.ioc.dependency.injection.AnnotationDependencyInjectionResolutionDemo,
	 * methodName=null, parameterTypes=null, parameterIndex=0,
	 * fieldName=userOptional,
	 * required=false,
	 * eager=true,
	 * nestingLevel=1,
	 * containingClass=null,
	 * resolvableType=java.util.Optional<org.geekbang.thinking.in.spring.ioc.overview.domain.User>,
	 * typeDescriptor=@org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired java.util.Optional<org.geekbang.thinking.in.spring.ioc.overview.domain.User>
	 *     )
	 */
    @MyAutowired
    private Optional<User> userOptional; // superUser

	// 使用JSR330注解
    @Inject
    private User injectedUser;

	@Resource
	private User resourceUser;

	/**
	 * DependencyDescriptor(
	 * declaringClass=class org.geekbang.thinking.in.spring.ioc.dependency.injection.AnnotationDependencyInjectionResolutionDemo,
	 * methodName=null, parameterTypes=null, parameterIndex=0,
	 * fieldName=myInjectedUser,
	 * required=true,
	 * eager=true,
	 * nestingLevel=1,
	 * containingClass=null,
	 * resolvableType=org.geekbang.thinking.in.spring.ioc.overview.domain.User,
	 * typeDescriptor=@org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser org.geekbang.thinking.in.spring.ioc.overview.domain.User)
	 */
	@InjectedUser
    private User myInjectedUser;

	// 加上static字段后，当前Bean会在AnnotationDependencyInjectionResolutionDemo实例化之前优先加载
    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor2() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired + @Inject +  新注解 @InjectedUser
        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
                new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    @Scope
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // 期待输出 superUser Bean
		System.out.println("demo.lazyUser = " + demo.lazyUser);
		System.out.println("demo.user = " + demo.user);
        System.out.println("demo.injectedUser = " + demo.injectedUser);

        // 期待输出 user superUser
        System.out.println("demo.users = " + demo.users);
        // 期待输出 superUser Bean
        System.out.println("demo.userOptional = " + demo.userOptional);
        // 期待输出 superUser Bean
        System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

		System.out.println("demo.resourceUser = " + demo.resourceUser);


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
