package org.tarena.note.web.controller.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.BookService;

/**
 * 与笔记本有关的Controller层：
 * 		根据userId加载显示笔记本列表
 * @author 全文超
 * 2016-05-11 16:04:51
 *
 */


@Controller
@RequestMapping("/notebook")
public class LoadBooksController {
	
	@Autowired
	private BookService bookService;  //将BookService接口的实现类注入给成员变量
	//而BookService接口的实现类只有一个(NoteBookServiceImpl)，
	//所以就会将该实现类注入给该Controller的成员变量
	
	@RequestMapping("/loadbooks")
	@ResponseBody
	public NoteResult execute(String userId){
		NoteResult result = bookService.LoadBooksByUserId(userId);
		return result;
	}
}
