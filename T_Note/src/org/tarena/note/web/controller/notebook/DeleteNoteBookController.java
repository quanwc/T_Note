package org.tarena.note.web.controller.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.BookService;

/**
 * 与笔记本有关的Controller层：
 * 		根据bookId删除笔记本
 * @author 全文超
 * 2016-05-19 15:27:25
 *
 */


@Controller
@RequestMapping("/notebook")
public class DeleteNoteBookController {
	
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public NoteResult execute(String bookId){
		NoteResult result = bookService.delete(bookId);
		return result;
	}
}
