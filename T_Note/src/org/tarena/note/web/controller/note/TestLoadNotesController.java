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
 * 测试LoadNotesController类
 * @author 全文超
 * 2016-05-12 11:04:41
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)  //将spring与junit结合
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestLoadNotesController {
	
	@Autowired  //注入需要测试的Controller
	private LoadNotesController controller;
	
	
	private MockMvc mockMvc;  //发送执行http请求
	
	
	@Before
	public void init(){  //初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void test1() throws Exception{
		RequestBuilder request =  MockMvcRequestBuilders
			.post("/note/loadnotes.do")
			.param("bookId", "1d46f5db-f569-4c05-bdba-75106108fcba");
		
		MvcResult result = mockMvc.perform(request).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
			
	}
	
}
