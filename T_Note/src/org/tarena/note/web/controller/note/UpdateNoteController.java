package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller层：
 * 		更新笔记信息
 * @author 全文超
 * 2016-05-16 10:35:30
 *
 */

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	
	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping("/update")
	@ResponseBody
	public NoteResult execute(String noteId, String noteTitle, String noteBody){
		
		NoteResult result = noteService.update(noteId, noteTitle, noteBody);
		return result;
	}
	
}
