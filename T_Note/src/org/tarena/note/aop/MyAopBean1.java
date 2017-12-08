package org.tarena.note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面：
 * 		共通的功能。将该切面的功能切入到切入点中
 * @author 全文超
 * 2016-05-21 17:30:16
 *
 */


@Component  //扫描到Spring容器中
@Aspect  //将该组建定义为切面
public class MyAopBean1 {
	
	//通过前置通知，作用到MyServiceImpl类的所有方法上
	//@Before：括号中写的是要切入的目标点
	@Before("within(org.tarena.note.aop.MyServiceImpl)")
	public void log(){
		System.out.println("跟踪日志操作");
	}
}
