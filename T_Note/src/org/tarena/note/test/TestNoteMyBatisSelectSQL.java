package org.tarena.note.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteParams;

/**
 * 测试MyBatis动态sql:
 * 					批量查询
 * @author 全文超
 * 2016-05-28 11:39:48
 *
 */
public class TestNoteMyBatisSelectSQL {
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
		NoteParams params = new NoteParams();
		params.setTitle("%j%");
		params.setStatus("2");
		List<Note> list = noteDao.findNotes(params);
		for(Note n : list){
			System.out.println(n);
		}
		System.out.println("共" + list.size() + "条记录");
	}
	
}
