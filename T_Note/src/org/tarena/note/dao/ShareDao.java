package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.Share;

/**
 * 分享(Share)接口：
 * 			与ShareMapper.xml文件对应
 * @author 全文超
 * 2016-05-18 09:39:45
 *
 */
public interface ShareDao {
	//保存分享的笔记
	int save(Share share);
	
	//根据NoteId查询笔记
	Share findByNoteId(String noteId);
	
	//查询笔记
	List<Share> findLikeTitle(String title);
}
