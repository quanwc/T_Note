package org.tarena.note.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 全文超
 * 2016-05-08 09:34:08
 *
 */
public class User implements Serializable{
	
	//定义属性：  与cn_user表中的字段一致
	private String cn_user_id;	//用户ID
	private String cn_user_name;  //用户名
	private String cn_user_password;  //密码
	private String cn_user_token;  //令牌。
	//每个请求过来时只需要携带令牌号即可，相对于session，令牌号可以在多态tomcat上使用。
	
	private String cn_user_desc;  //昵称
	
	
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cn_user_desc) {
		this.cn_user_desc = cn_user_desc;
	}
	
	
	@Override
	public String toString(){
		return cn_user_id + ":  " + cn_user_name + ", " + cn_user_password 
				+ ", " + cn_user_token + ", " + cn_user_desc;
	}
}
