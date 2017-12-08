package org.tarena.note.web.controller.user;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tarena.note.entity.NoteResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller的测试类：
 * 		使用junit测试Controller(SpingMVC)层的逻辑，
 * 	不用使用页面来发送请求来测试
 * 
 * spring-test测试工具
 * 		基于junit4使用
 * 		引入spring-test.jar
 * 		
 * @author 全文超
 * 2016-05-10 18:36:54
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)  //将spring与junit结合
@ContextConfiguration(locations={"classpath:applicationContext.xml"})

public class TestUserRegistController {
	
	@Autowired  //注入需要测试的Controller
	private UserRegistController controller;
	
	
	private MockMvc mockMvc;  //发送执行http请求
	
	
	@Before
	public void init(){  //初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	
	@Test
	public void test1() throws Exception{
		
		RequestBuilder request =  MockMvcRequestBuilders
		.post("/user/regist.do")
		.param("cn_user_name", "structs")
		.param("cn_user_password", "123456")
		.param("cn_user_desc", "structs");
		
		//发送执行一个HTTP请求         
		//andExpect：断言     //isOk(): 代表请求发送过去是不是ok的，http的响应code是200
		//andReturn：返回结果
		MvcResult result = mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		
		//得到响应信息中的json字符串
		String jsonStr = result.getResponse().getContentAsString();
		System.out.println(jsonStr);
		//将返回的json字符串转换成java对象
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = mapper.readValue(jsonStr, NoteResult.class);//将json字符串还原为java对象
		//使用断言判断    //structs， 123456 --》0
		Assert.assertEquals(0, noteResult.getStatus());
	}
	
	
	@Test
	public void test2() throws Exception{
		
		RequestBuilder request =  MockMvcRequestBuilders
				.post("/user/regist.do")
				.param("cn_user_name", "demo")
				.param("cn_user_password", "123456")
				.param("cn_user_desc", "demo");
				
				//发送执行一个HTTP请求         
				//andExpect：断言     //isOk(): 代表请求发送过去是不是ok的，http的响应code是200
				//andReturn：返回结果
				MvcResult result = mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
				
				//得到响应信息中的json字符串
				String jsonStr = result.getResponse().getContentAsString();
				System.out.println(jsonStr);
				//将返回的json字符串转换成java对象
				ObjectMapper mapper = new ObjectMapper();
				NoteResult noteResult = mapper.readValue(jsonStr, NoteResult.class);//将json字符串还原为java对象
				//使用断言判断    //demo， 123456 --》1
				Assert.assertEquals(1, noteResult.getStatus());
	}
}
