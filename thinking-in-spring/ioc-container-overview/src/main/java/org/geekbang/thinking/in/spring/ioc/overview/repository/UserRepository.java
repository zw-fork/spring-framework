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
package org.geekbang.thinking.in.spring.ioc.overview.repository;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collection;

/**
 * 用户信息仓库
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class UserRepository {

    //依赖来源一：用户自定义的Bean。如：User对象
    private Collection<User> users; // 自定义 Bean，实时注入

    // 依赖来源二：容器內建依赖。如：ObjectFactory、BeanFactory
    private BeanFactory beanFactory; // 內建非 Bean 对象（依赖），实时注入

    // 通过延迟注入ApplicationContext
    //ObjectFactory.getObject() -> ApplicationContext
    private ObjectFactory<ApplicationContext> objectFactory;

    // 通过延迟注入User
    //ObjectFactory.getObject() -> User
    private ObjectFactory<User> userObjectFactory;

    // 依赖来源三：容器内建的Bean。比如：Environment
    private Environment environment;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }


    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory)
    {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<User> getUserObjectFactory()
    {
        return userObjectFactory;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
