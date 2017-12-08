package org.tarena.note.web.controller.note;

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

/**
 * 测试AddNoteController类
 * @author 全文超
 * 2016-05-15 10:12:27
 *
 */



@RunWith(SpringJUnit4ClassRunner.class)  //将spring与junit结合
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestAddNoteController {

	
	@Autowired  //注入需要测试的Controller
	private AddNoteController controller;
	
	
	private MockMvc mockMvc;  //发送执行http请求
	
	
	@Before
	public void init(){  //初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void test1() throws Exception{
		RequestBuilder request =  MockMvcRequestBuilders
			.post("/note/add.do")
			.param("bookId", "59b354f8-47ae-437d-a432-7d28736bd894")
			.param("userId", "48595f52-b22c-4485-9244-f4004255b972")
			.param("noteTitle", "Spring02");
		
		MvcResult result = mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
			
	}
	

}
