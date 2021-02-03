package com.zw.enablexxx;

import com.zw.lifecycle.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Person enablePerson() {
		Person studentBean = new Person();
        studentBean.setName("enable person");
        return studentBean;
    }
}