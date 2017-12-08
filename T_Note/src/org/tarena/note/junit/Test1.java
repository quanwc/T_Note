package org.tarena.note.junit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tarena.note.util.NoteException;

/**
 * junit4单元测试
 * @author 全文超
 * 2016-05-10 17:21:49
 *
 */
public class Test1 {
	
	@BeforeClass  //类加载时候执行一次，类似于java的static块
	public static void mystatic(){
		System.out.println("--mystatic--");
	}
	
	
	@Before  //每个test方法执行前都会调用一次该方法
	public void init(){
		System.out.println("--init--");
	}
	
	
	@After
	public void destroy(){  //每个test方法执行后都会调用一次该方法
		System.out.println("--destroy--");
	}
	
	
	
	@Test
	public void test1(){
		System.out.println("--test1--");
	}
	
	
	@Test
	public void test2(){
		System.out.println("--test2--");
		throw new NoteException("模拟异常");
	}
	
	@Test
	public void test3(){
		System.out.println("--test3--");
		
		String act = "hello";
		Assert.assertEquals("Hello", act);  //断言
	}
	
	
}
