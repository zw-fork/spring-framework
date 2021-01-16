package com.zw.lifecycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

	protected final static Log logger = LogFactory.getLog(PersonController.class.getClass());


	@Async
	public String hello() {
		System.out.println("当前线程：" + Thread.currentThread().getName());
		return "Controller Person";
	}
}
