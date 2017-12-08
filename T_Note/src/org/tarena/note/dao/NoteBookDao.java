package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.NoteBook;

/**
 * 笔记本(NoteBookDao)接口：
 * 		与NoteBookMapper.xml文件对应
 * @author 全文超
 * 2016-05-11 15:06:06
 *
 */
public interface NoteBookDao {
	
	//集合的关联映射
	NoteBook findById(String bookId);
	
	List<NoteBook> findByUserId(String userId);  //根据用户id查询笔记本列表
	
	int save(NoteBook book);  //添加笔记本
	
	//查询笔记本
	NoteBook findByNameAndUserId(NoteBook book);
	
	//删除笔记本
	int delete(String bookId);
	
	//更新笔记本标题
	int updateName(NoteBook book);
}
