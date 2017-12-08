package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

/**
 * 与笔记(Note)有关的业务层接口:
 * 		用来访问访问与Note有关的dao，和处理相关的逻辑判断处理
 * @author 全文超
 * 2016-05-12 10:52:16
 *
 */
public interface NoteService {
	
	//根据bookId查询笔记
	NoteResult loadNotesByBookId(String bookId);
	
	//添加笔记
	NoteResult createNote(String bookId, String userId, String noteTitle);
	
	//根据笔记id查询信息
	NoteResult loadNoteById(String noteId);
	
	//跟新笔记内容
	NoteResult update(String noteId, String noteTitle, String noteBody);
	
	//删除笔记
	NoteResult delete(String noteId);
	
	//分享笔记
	NoteResult share(String noteId);
	
	//查询笔记
	NoteResult search(String keyword);
}
