package org.tarena.note.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;

/**
 * 与用户注册有关的Controller层
 * @author 全文超
 * 2016-05-09 22:28:56
 *
 */


@Controller
@RequestMapping("/user")
public class UserRegistController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/regist")  //对应：user/regist.do
	@ResponseBody  //返回json形式的字符串
	public NoteResult execute(User user){
		NoteResult result = userService.registUser(user);
		return result;
	}
}
