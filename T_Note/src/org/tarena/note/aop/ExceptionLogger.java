package org.tarena.note.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 基于AOP，将异常信息写入到文件中
 * 通知：发生异常后执行（异常通知）  @AfterThrowing
 * 方面：将异常对象写入文件
 * 切入点：往Controller方法切入
 * 		within(org.tarena.note.web.controller..*)
 * 
 * @author 全文超
 * 2016-05-23 14:56:28
 *
 */


@Component
@Aspect
public class ExceptionLogger {
	
	Logger logger = Logger.getLogger(ExceptionLogger.class);
	
	@AfterThrowing( throwing="ex",
			pointcut="within(org.tarena.note.web.controller..*)")
	
	//使用Logger输出异常
	public void log(Exception ex){
		logger.error("occur ex：" + ex);
		StackTraceElement[] st = ex.getStackTrace();
		for(StackTraceElement e : st){
			logger.error(e.toString());
		}
	}
	
	public void mylog(Exception ex){
		//记录异常对象
		try {
			FileWriter out = new FileWriter("D:\\error.log");
			PrintWriter pw = new PrintWriter(out);
			pw.println("====异常信息如下：====");
			ex.printStackTrace(pw);//将此 throwable及其追踪输出到指定的PrintWriter
			pw.flush();
			pw.close();
			out.close();
		} catch (IOException e) {
			System.out.println("记录异常信息出错");
		}
	}
	
}
