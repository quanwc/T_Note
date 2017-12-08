package org.tarena.note.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.User;

/**
 * junit的使用
 * @author 全文超
 * 2016-05-10 18:03:15
 *
 */
public class TestSpring {
	
	UserDao userDao = null;
	AbstractApplicationContext ac = null;
	
	@Before  //在每个test方法执行前都会执行
	public void init(){
		String conf = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		
		userDao = ac.getBean("userDao", UserDao.class);
	}
	
	
	@After
	public void destroy(){
		System.out.println("close容器");
		ac.close();
	}
	
	
	/**
	 * 测试配置工作是否成功，同时测试UserMapper.xml文件中的findByName
	 */
	@Test
	public void test1(){
		
		User user = userDao.findByName("demo");

		Assert.assertNotNull(user);  //断言user不为null，如果为null，那么就会报错
	}
	
	
	
	/**
	 * 测试测试UserMapper.xml文件中的save()方法
	 */
	@Test
	public void test2(){
		
		User user = userDao.findByName("dddd");

		Assert.assertNull(user);  //断言user为null
	}

}
