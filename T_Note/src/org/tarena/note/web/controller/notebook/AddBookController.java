package org.tarena.note.web.controller.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.BookService;

/**
 * 与笔记本有关的Controller层：
 * 			添加笔记本
 * @author 全文超
 * 2016-05-14 15:41:27
 *
 */


@Controller
@RequestMapping("/notebook")
public class AddBookController {
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult execute(String userId, String bookName){
		NoteResult result = bookService.create(userId, bookName);
		System.out.println("111111");
		return result;
	}
}
