package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.service.impl.NoteServiceImpl;

/**
 * 与笔记有关的Controller层：
 * 		根据笔记Id加载笔记信息
 * @author 全文超
 * 2016-05-15 15:36:48
 *
 */


@Controller
@RequestMapping("/note")
public class LoadNoteController {
	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping("/load")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = noteService.loadNoteById(noteId);
		return result;
	}
	
}
