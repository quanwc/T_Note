package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteParams;

/**
 * 笔记(Note)接口：
 * 			与NoteMapper.xml文件对应
 * @author 全文超
 * 2016-05-12 10:32:30
 *
 */
public interface NoteDao {
	//根据笔记本的id查询其所有笔记
	List<Note> findByBookId(String bookId);  
	
	//添加笔记
	int save(Note note);
	
	//根据笔记id查询笔记信息
	Note findById(String noteId);
	
	//更新笔记内容
//	int update(Note note);
	
	//更新笔记状态(删除笔记)
//	int updateStatus(Note note);
	
	//统计笔记本中的笔记个数
	int countByBookId(String bookId);
	
	//MyBatis动态查询
	List<Note>findNotes(NoteParams params);
	
	//MyBatis动态更新
	int updateNotes(Note note);
	
	//MyBatis批量删除
	int deleteNotes(String [] ids);
	
	//对象关联加载
	List<Note> find1();
	List<Note>find2();
}
