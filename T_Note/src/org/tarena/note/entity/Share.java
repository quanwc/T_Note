package org.tarena.note.entity;

import java.io.Serializable;
/**
 * 分享笔记的实体类：
 * @author 全文超
 * 2016-05-18 09:11:47
 *
 */
public class Share implements Serializable {
	private String cn_share_id;
	private String cn_share_title;
	private String cn_share_body;
	private String cn_note_id;  //避免重复分享
	
	
	public String getCn_share_id() {
		return cn_share_id;
	}
	public void setCn_share_id(String cn_share_id) {
		this.cn_share_id = cn_share_id;
	}
	public String getCn_share_title() {
		return cn_share_title;
	}
	public void setCn_share_title(String cn_share_title) {
		this.cn_share_title = cn_share_title;
	}
	public String getCn_share_body() {
		return cn_share_body;
	}
	public void setCn_share_body(String cn_share_body) {
		this.cn_share_body = cn_share_body;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	
	
	
	@Override
	public String toString(){
		return "shareId:  " + cn_share_id + ", " + 
				cn_share_title + ", " + cn_share_body;
	}
}
