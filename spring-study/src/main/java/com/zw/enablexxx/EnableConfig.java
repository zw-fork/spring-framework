package com.zw.enablexxx;

import com.zw.lifecycle.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnableConfig {

    @Bean
    public Person enablePerson() {
		Person studentBean = new Person("zw");
        studentBean.setName("enable person");
        return studentBean;
    }
}