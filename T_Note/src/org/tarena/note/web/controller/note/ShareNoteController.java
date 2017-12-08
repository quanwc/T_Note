package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller层：
 * 				分享笔记
 * @author 全文超
 * 2016-05-18 10:02:17
 *
 */


@Controller
@RequestMapping("/note")
public class ShareNoteController {
	
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/share")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = noteService.share(noteId);
		return result;
	}
}
