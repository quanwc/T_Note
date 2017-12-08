package org.tarena.note.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteDao;

/**
 * MyBatis批量删除
 * @author 全文超
 * 2016-05-28 16:51:23
 *
 */
public class TestNoteMyBatisBatchDelete {
	NoteDao noteDao = null;
	AbstractApplicationContext ac = null;
	
	@Before  //在每个test方法执行前都会执行
	public void init(){
		String conf = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		
		noteDao = ac.getBean("noteDao", NoteDao.class);
	}
	
	
	@After
	public void destroy(){
		System.out.println("close容器");
		ac.close();
	}

	
	@Test
	public void test1(){
		String[] ids = {"ss19055-30e8-4cdc-bfac-97c6bad9518f",
				"ffc2cf21-78ed-4647-adb4-3e545613ef26",
				"fed920a0-573c-46c8-ae4e-368397846efd"};
		int rows = noteDao.deleteNotes(ids);
		System.out.println("删除记录: " + rows + "行");
		Assert.assertEquals(ids.length, rows);
	}
	
	
}
