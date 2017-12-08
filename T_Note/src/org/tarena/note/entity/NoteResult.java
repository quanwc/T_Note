package org.tarena.note.entity;

import java.io.Serializable;
/**
 * 实体类：
 * 		用来表示服务器返回的JSON数据格式：
 * @author 全文超
 * 2016-05-08 14:05:59
 *
 */
public class NoteResult implements Serializable {
	
	private int status;  //状态：0正常
	private String msg;  //消息： 用户名错误或密码错误
	private Object data;  //数据
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
