package org.tarena.note.web.controller.note;

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
 * 测试DeleteNoteController类
 * @author 全文超
 * 2016-05-15 23:30:55
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)  //将spring与junit结合
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestDeleteNoteController {


	@Autowired  //注入需要测试的Controller
	private DeleteNoteController controller;
	
	
	private MockMvc mockMvc;  //发送执行http请求
	
	@Before
	public void init(){  //初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void test() throws Exception{

		RequestBuilder request =  MockMvcRequestBuilders
				.post("/note/delete.do")
				.param("noteId", "c7ee01e7276c49329beaa681775a0653");
		
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
