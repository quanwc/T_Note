package org.tarena.note.test;

import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.User;

/**
 * 测试类：
 * @author 全文超
 * 2016-05-08 11:35:51
 *
 */
public class TestUserDao {

	/**
	 * 测试配置工作是否成功，同时测试UserMapper.xml文件中的findByName
	 */
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		
		UserDao userDao = ac.getBean("userDao", UserDao.class);
		User user = userDao.findByName("demo");
		if(user != null){
			System.out.println(user);
		}else{
			System.out.println("查无此人");
		}
		
		MapperScannerConfigurer m = ac.getBean("mapperScannerConfigurer", MapperScannerConfigurer.class);
		System.out.println(m);
	}
	
	
	
	/**
	 * 测试测试UserMapper.xml文件中的save()方法
	 */
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		
		UserDao userDao = ac.getBean("userDao", UserDao.class);
		User user = new User();
		user.setCn_user_id("1111");
		user.setCn_user_name("tarena");
		user.setCn_user_password("123456");
		userDao.save(user);
	}
	
	
}
