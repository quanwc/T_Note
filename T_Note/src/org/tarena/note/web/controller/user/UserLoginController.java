package org.tarena.note.web.controller.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteException;

/**
 * Controller层：
 * 		与用户登录有关的Controller
 * 
 * @author 全文超
 * 2016-05-08 15:58:52
 *
 */

@Controller
@RequestMapping("/user")
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	
	
	//对应： /user/login.do
	@RequestMapping("/login") // ".do"可省略
	@ResponseBody	//将该方法的返回值NoteResult对象以JSON形式返回
	public NoteResult execute(HttpServletRequest request){//参数名与页面的name属性值应保持一致
		
			//获取请求头身份信息
			String author = request.getHeader("Authorization");
			
			//检查身份信息，之后再检查用户名和密码的正确性
			NoteResult result = userService.checkLogin(author);
			
			return result;
		
	}
	
}
