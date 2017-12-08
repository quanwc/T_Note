package org.tarena.note.web.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

/**
 * 与笔记有关的Controller层：
 * 		根据搜索框查询笔记，是对分享过的笔记做查询，查询的是cn_share表。
 * @author 全文超
 * 2016-05-18 12:20:49
 *
 */


@Controller
@RequestMapping("/note")
public class SearchShareController {
	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping("/search")
	@ResponseBody
	public NoteResult execute(String keyword){
		System.out.println("---");
		NoteResult result = noteService.search(keyword);
		return result;
	}
}
