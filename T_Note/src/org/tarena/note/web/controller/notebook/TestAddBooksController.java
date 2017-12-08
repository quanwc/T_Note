package org.tarena.note.web.controller.notebook;

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
 * 测试AddBookController类
 * @author 全文超
 * 2016-05-14 15:44:52
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)  //将spring与junit结合
@ContextConfiguration(locations={"classpath:applicationContext.xml"})

public class TestAddBooksController {

	@Autowired  //注入需要测试的Controller
	private AddBookController controller;
	
	
	private MockMvc mockMvc;  //发送执行http请求
	
	@Before
	public void init(){  //初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void test() throws Exception{

		RequestBuilder request =  MockMvcRequestBuilders
				.post("/notebook/add.do")
				.param("userId", "48595f52-b22c-4485-9244-f4004255b972")
				.param("bookName", "C++");
		
		MvcResult mvcResult = mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		
		//获取响应信息
		String jsonStr = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		NoteResult result = mapper.readValue(jsonStr, NoteResult.class);
		//断言测试
		Assert.assertEquals(0, result.getStatus());
	
	}
}
