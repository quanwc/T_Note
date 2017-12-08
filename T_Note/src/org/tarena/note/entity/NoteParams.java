package org.tarena.note.entity;
/**
 * MyBatis动态SQL：
 * 		使用该实体类来封装数据
 * @author 全文超
 * 2016-05-28 11:07:26
 *
 */
public class NoteParams {
	private String title;  //笔记的标题
	private String status;  //笔记的状态
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
