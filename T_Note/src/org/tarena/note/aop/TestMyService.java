package org.tarena.note.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 * 
 * AOP: 
 * 		可以在不修改原有代码的基础上，可以将新的功能增加到原有组件中
 * 
 * @author 全文超
 * 2016-05-21 16:56:44
 *
 */

public class TestMyService {
	
	private MyService myservice;
	
	@Before
	public void init(){
		String conf = "org/tarena/note/aop/spring-aop-annotation.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		myservice = ac.getBean("myservice", MyService.class);
	}
	
	
	@Test
	public void test1(){
		System.out.println(myservice.getClass().getName());//$Proxy10
		myservice.f1();
	}
	
	@Test
	public void test2(){
		myservice.f2();
	}
}
