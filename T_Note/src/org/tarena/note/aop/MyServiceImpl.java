package org.tarena.note.aop;

import org.springframework.stereotype.Service;

/**
 * MyService接口的实现类
 * @author 全文超
 * 2016-05-21 16:40:17
 *
 */


@Service("myservice")
public class MyServiceImpl implements MyService {

	public void f1() {
		System.out.println("执行f1业务处理");

	}

	public void f2() {
		System.out.println("执行f2业务处理");
	}

}
