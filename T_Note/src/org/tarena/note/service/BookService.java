package org.tarena.note.service;

import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;

/**
 * 与笔记本(NoteBook)有关的业务层接口
 * @author 全文超
 * 2016-05-11 15:56:20
 *
 */
public interface BookService {
	
	//根据用户id查询笔记本列表
	NoteResult LoadBooksByUserId(String userId);
	
	//添加笔记本， 需要页面传入用户的id，笔记本名称
	NoteResult create(String userId, String bookName); 
	
	//删除笔记本
	NoteResult delete(String bookId);
	
	//更新笔记本标题
	NoteResult updateBookName(String userId, String bookId, String bookName);
}
