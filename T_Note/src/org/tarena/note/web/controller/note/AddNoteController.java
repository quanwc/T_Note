package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller：
 * 		添加笔记
 * @author 全文超
 * 2016-05-15 10:07:11
 *
 */


@Controller
@RequestMapping("/note")
public class AddNoteController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult execute(String bookId, String userId, String noteTitle){
		NoteResult result = noteService.createNote(bookId, userId, noteTitle);
		return result;
	}
}
