package org.tarena.note.web.controller.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.BookService;

/**
 * 与笔记本有关的Controller层：
 * 		修改笔记本名称
 * @author 全文超
 * 2016-05-20 15:56:48
 *
 */


@Controller
@RequestMapping("/notebook")
public class UpdateBookController {
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/update")
	@ResponseBody
	public NoteResult execute(String userId, String bookId, String bookName){
		NoteResult result = 
				bookService.updateBookName(userId, bookId, bookName);
		return result;
	}
	
}
