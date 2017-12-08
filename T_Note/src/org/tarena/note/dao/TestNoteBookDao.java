package org.tarena.note.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteBook;

/**
 * 测试NoteBookDao接口的方法
 * @author 全文超
 * 2016-05-11 15:24:48
 *
 */
public class TestNoteBookDao {
	
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		
		NoteBookDao bookDao = ac.getBean("noteBookDao", NoteBookDao.class);
		
											//传入demo的id
		List<NoteBook> books = bookDao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for(NoteBook book : books){
			System.out.println(book);
		}
	}
}
