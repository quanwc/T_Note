package org.tarena.note.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteException;
import org.tarena.note.util.NoteUtil;
/**
 * UserService接口的实现类
 * @author 全文超
 * 2016-05-08 14:29:19
 *
 */

@Service
@Transactional(isolation=Isolation.SERIALIZABLE)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	/**
	 * 检查登录信息的正确性：
	 * 返回值是NoteResult对象 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public NoteResult checkLogin(String name, String pwd) {
		NoteResult result = new NoteResult();
		User user = userDao.findByName(name);
		if(user == null){  //用户名为空
			result.setStatus(1);
			result.setMsg("用户名不存在");
		}else{  //用户名存在，比较密码
			
			//将用户输入的密码采用md5加密
			String md5_pwd = NoteUtil.md5(pwd);
			
			//将md5加密后的结果和数据库密码比较
			if(user.getCn_user_password(). equals(md5_pwd)){
				result.setStatus(0);
				result.setMsg("用户名和密码正确");
				
				//登录成功后，要跳转到edit页面。进入edit页面时，需要传入用户id，之后加载用户的笔记本信息。
				//将userId放到cookie中，登录成功，一进入edit页面，就从cookie中取出userId，
				//发送ajax请求，根据userId查询笔记本信息，之后加载显示在页面
				
				//传出userId和用户token令牌
				String token = NoteUtil.createToken();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("userId", user.getCn_user_id());
				data.put("userToken", token);
				result.setData(data);//返回数据
				//将token令牌号跟新到数据报cn_user
				userDao.updateToken(data);
			}else{
				result.setStatus(2);
				result.setMsg("密码不正确");
			}
		}
		return result;
	}


	
	
	/**
	 * 检查身份信息，之后调用上面的重载方法检查用户名和密码的正确性
	 */
	public NoteResult checkLogin(String author) {
		try {
			//解析身份信息， 获取"加密消息"部分的消息
			String base64_msg = author.split(" ")[1];
			System.out.println("密文：" + base64_msg);
			
			//将"加密消息"还原成明文: "用户名:密码"
			byte[] output = Base64.decodeBase64(base64_msg);  //解密，
			//页面中以Base64形式加密，服务器端同样以Base64解密
			
			String msg = new String(output, "utf-8");
			System.out.println("明文：" + msg);
			
			//切割"用户名:密码"，获取用户名和密码
			String name = msg.split(":")[0];
			String password = msg.split(":")[1];
			System.out.println("name：" + name);
			System.out.println("password:" + password);
			
			//检查用户名和密码正确性
			NoteResult result = checkLogin(name, password);//调用上面的重载函数
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoteException("身份验证错误");
		}
		
	}




	/**
	 * 注册用户，将用户插入到cn_user表中
	 */
	public NoteResult registUser(User user) {
		
		//页面传进来的有：用户名，昵称，密码
		
		
		NoteResult result = new NoteResult();
		
		//检测用户名是否重复
		User uu = userDao.findByName(user.getCn_user_name());
		if( uu!= null){  //该用户在数据库中已经存在
			result.setStatus(1);
			result.setMsg("用户名已存在");
			return result;  
		}
		//设置ID
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);
		
		//密码加密
		String md5_pwd = NoteUtil.md5(user.getCn_user_password());
		user.setCn_user_password(md5_pwd);
		
		//调用userDao
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		
		//模拟发生异常，事务会撤销前面的save操作
		String s = null;
		s.length();
		
		return result;
		
	}
	
	
	
	
}	
