package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;

/**
 * 与用户(User)有关的业务层的接口：
 * @author 全文超
 * 2016-05-08 13:02:57
 *
 */
public interface UserService {
	
	/**
	 * 检测用户名和密码的正确性：
	 * 返回值是NoteResult类型，返回给Controller层，Controller会将NoteResult变为JSON输出
	 */
	NoteResult checkLogin(String name, String pwd);
	
	NoteResult checkLogin(String author);
	
	NoteResult registUser(User user);
}	
