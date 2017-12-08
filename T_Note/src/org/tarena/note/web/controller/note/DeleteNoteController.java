package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller：
 * 		删除笔记： 修改笔记的cn_note_status_id属性值
 * @author 全文超
 * 2016-05-15 23:27:49
 *
 */


@Controller
@RequestMapping("/note")
public class DeleteNoteController {

	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/delete")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = noteService.delete(noteId);
		return result;
	}
}
