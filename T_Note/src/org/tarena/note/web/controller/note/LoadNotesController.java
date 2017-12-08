package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller层：
 * 		根据bookId加载笔记列表
 * @author 全文超
 * 2016-05-12 11:00:36
 *
 */


@Controller
@RequestMapping("/note")
public class LoadNotesController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/loadnotes")
	@ResponseBody
	public NoteResult execute(String bookId){
		NoteResult result = noteService.loadNotesByBookId(bookId);
		return result;
	}
}
