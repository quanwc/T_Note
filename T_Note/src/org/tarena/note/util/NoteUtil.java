package org.tarena.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * 工具类
 * @author 全文超
 * 2016-05-09 11:28:48
 *
 */
public class NoteUtil {
	
	
	/**
	 * 采用MD5摘要算法处理信息：
	 * 
	 * 登录时： 将输入的密码加密，与之前数据库中加密后的密码相比较。
	 * 	比较的是加密结果，加密结果相同，则明文一定相同
	 * @param msg
	 * @return
	 */
	public static String md5(String msg){
		
		//获取MD5加密码对象
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");//选择加密的算法类型
			
			//将msg字符串加密处理
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);//需要传入字节数组
			//System.out.println(output.length);//经过MD5算法处理之后，长度为16
			
			//将加密后的output结果转成字符串
			//return new String(output);  //使用new String的方式：结果乱码，所以使用Base64算法
			
			//采用Base64算法将字节数组转成字符串
			String base64_msg = Base64.encodeBase64String(output);
			return base64_msg;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 该方法用来生成主建id，类型类String类型
	 * @return
	 */
	public static String createId(){
		UUID uid = UUID.randomUUID();
		return uid.toString().replaceAll("-", "");
		
	}
	
	
	public static String createToken(){
		return createId();
	}
	
	
	public static void main(String[] args) {
//		System.out.println(md5("123456"));
//		System.out.println(md5("123456"));
		System.out.println(createId());
		System.out.println(createId());
	}
}
