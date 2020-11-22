package com.zw.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LifeCylceConfig {
  @Bean(initMethod = "myInit", destroyMethod = "myDestory")
  Person person() {
    return new Person();
  }
}