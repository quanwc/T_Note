package org.tarena.note.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;

/**
 * 集合的关联映射：
 * 		显示某个笔记本名称，以及包含的笔记信息
 * @author 全文超
 * 2016-05-30 10:38:33
 *
 */
public class TestCollection {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteBookDao bookDao = 
			ac.getBean("noteBookDao",NoteBookDao.class);
		
		NoteBook book = bookDao.findById("a1669286-b171-4a06-b2fc-2dfe5afdb0ec");
		System.out.println("笔记本名称: "+book.getCn_notebook_name());
		System.out.println("创建时间: "+book.getCn_notebook_createtime());
		
		System.out.println("---所有笔记信息---");
		for(Note n : book.getNotes()){
			System.out.println(n.getCn_note_title());
		}
		
	}
	
	
}
