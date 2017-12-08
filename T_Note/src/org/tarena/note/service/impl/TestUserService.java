package org.tarena.note.service.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

/**
 * 测试service
 * @author 全文超
 * 2016-05-10 18:20:57
 *
 */
public class TestUserService {
	
	AbstractApplicationContext ac = null;
	UserService userService = null;
	
	
	@Before
	public void init(){
		String conf = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		userService = ac.getBean("userServiceImpl", UserService.class);
	}
	
	
	
	
	@Test	//测试service层组件: demo,123456 --->0
	public void test1(){
		NoteResult result = userService.checkLogin("demo", "123456");
		
		//断言，输入正确的用户名和密码，返回的状态值为0
		Assert.assertEquals(0, result.getStatus());
	}
	
	
	@Test	//测试service层组件: dddd,123456 --->1
	public void test2(){
		NoteResult result = userService.checkLogin("dddd", "123456");
		
		//断言，输入正确的用户名为空，返回的状态值为1
		Assert.assertEquals(1, result.getStatus());
	}
	
	
	@Test	//测试service层组件: demo, 123 --->2
	public void test3(){
		NoteResult result = userService.checkLogin("demo", "123");
		
		//断言，输入密码有误，返回的状态值为3
		Assert.assertEquals(2, result.getStatus());
	}
	
	
	@After
	public void destroy(){
		ac.close();
	}
	
	
}
