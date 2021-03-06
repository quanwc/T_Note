package org.tarena.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 实体类：
 * 		笔记本
 * @author 全文超
 * 2016-05-11 14:39:37
 *
 */
public class NoteBook implements Serializable{
	
	//定义属性：  与cn_notebook表中的字段一致
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;  //该属性对应数据库是text类型
	private Timestamp cn_notebook_createtime;  //该字段对应数据库中是timestamp类型，
						//所以对应实体类的类型为java.sql.Timestamp类型
	
	//关联属性，用于加载相关的笔记信息
	private List<Note> notes;
	
	
	
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	public void setCn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	public void setCn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setCn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}
	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setCn_notebook_createtime(Timestamp cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	
	
	@Override
	public String toString(){
		return "id:  " + cn_notebook_id + ", " + cn_notebook_name;
	}
	
}
