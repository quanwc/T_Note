package org.tarena.note.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Note;

/**
 * 单个对象的关联映射:
 * 	显示一个列表： 笔记ID 笔记标题 创建时间 所属笔记本名
 * @author 全文超
 * 2016-05-28 20:14:35
 *
 */
public class TestAssociation {
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
		List<Note> list = noteDao.find2();
		for(Note n : list){
			if(n.getBook() != null){
				System.out.println(n + ", " + n.getBook().getCn_notebook_name());
			}
		}
	}
	
}
