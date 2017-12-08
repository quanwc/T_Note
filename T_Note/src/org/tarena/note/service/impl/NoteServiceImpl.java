package org.tarena.note.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.dao.ShareDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.Share;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;
/**
 * NoteService接口的实现类
 * @author 全文超
 * 2016-05-12 10:55:11
 *
 */


@Service
public class NoteServiceImpl implements NoteService {

	
	@Autowired
	private NoteDao noteDao;
	
	@Autowired
	private ShareDao shareDao;
	
	public NoteResult loadNotesByBookId(String bookId) {
		
		NoteResult result = new NoteResult();
		
		if(bookId!=null && !bookId.equals("")){
			List<Note> list = noteDao.findByBookId(bookId);
			result.setData(list);
		}
		
		result.setStatus(0);
		result.setMsg("加载笔记成功");
		return result;
	}

	
	//添加笔记
	@Transactional
	public NoteResult createNote(String bookId, String userId, String noteTitle) {
		//TODO 参数格式检查
		
		//添加笔记操作
		NoteResult result = new NoteResult();
		
		Note note = new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_title(noteTitle);
		//设置noteId
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("1");
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		
		//调用dao
		noteDao.save(note);
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(noteId);//将noteId返回
		return result;
	}


	//根据笔记id加载笔记信息
	public NoteResult loadNoteById(String noteId) {
		
		NoteResult result = new NoteResult();
		
		Note note = noteDao.findById(noteId);
		result.setStatus(0);
		result.setMsg("加载笔记信息成功");
		result.setData(note);
		
		return result;
	}

	
	//更新笔记
	public NoteResult update(String noteId, String noteTitle, String noteBody) {
		NoteResult result = new NoteResult();
		
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		System.out.println("body:" + noteBody);
		//调用dao
		noteDao.updateNotes(note);
		
		result.setStatus(0);
		result.setMsg("更新笔记成功");
		
		return result;
	}

	
	//删除笔记
	public NoteResult delete(String noteId) {
		NoteResult result = new NoteResult();
		
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");  //删除状态
		noteDao.updateNotes(note);//跟新笔记的状态
		
		result.setStatus(0);	
		result.setMsg("删除笔记成功");
		return result;
	}


	//分享笔记
	public NoteResult share(String noteId) {
		NoteResult result = new NoteResult();
		
		//检测noteId笔记是否被分享过
		Share share_tmp = shareDao.findByNoteId(noteId);
		if(share_tmp!=null){//已经分享过
			result.setStatus(1);
			result.setMsg("该笔记已被分享过");
			return result;
		}
		
		
		//根据noteId去cn_note表查询标题，内容
		Note note = noteDao.findById(noteId);
		Share share = new Share();
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_note_id(note.getCn_note_id());
		//调用dao
		shareDao.save(share);
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		return result;
	}


	//查询笔记
	public NoteResult search(String keyword) {
		
		NoteResult result = new NoteResult();
		
		String key = "%";//查询所有信息
		if(keyword!=null && !keyword.equals("")){
			key = "%" + keyword.trim() + "%";
		}
		
		List<Share> list = shareDao.findLikeTitle(key);
		System.out.println("list:" + list.size());
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(list);
		return result;
	}


	

}
