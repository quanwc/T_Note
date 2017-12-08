package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookDao;
import org.tarena.note.dao.NoteDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.BookService;
import org.tarena.note.util.NoteUtil;
/**
 * BookService接口的实现类
 * @author 全文超
 * 2016-05-11 15:58:13
 *
 */


@Service
@Transactional  //默认：@Transactional(readOnly=false)，对数据库进行查询操作，还有增删改操作 
public class NoteBookServiceImpl implements BookService {

	@Autowired
	private NoteBookDao bookDao;
	
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * 根据userId，加载所有笔记本
	 */
	@Transactional(readOnly=true) //readOnly，只读：只对数据库进行查询操作
	public NoteResult LoadBooksByUserId(String userId) {
		
		NoteResult result = new NoteResult();
		
		if(userId!=null && !userId.equals("")){
			List<NoteBook> list = bookDao.findByUserId(userId);
			result.setData(list);
			//将查询到的结果封装到list集合，之后将list集合方法到NoteResult对象的data属性上
		
		}
		result.setStatus(0);
		result.setMsg("加载笔记本成功");
		
		return result;
	}

	
	
	/**
	 * 添加笔记本
	 */
	public NoteResult create(String userId, String bookName) {
		
		NoteResult result = new NoteResult();
		
		//检查格式参数：bookName是否为空，是否重名
		if(bookName==null || bookName.equals("")){
			result.setStatus(1);
			result.setMsg("笔记本名称为空");
			return result;
		}
		
		
		NoteBook book = new NoteBook();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		
		//检查是否重名
		NoteBook nbook = bookDao.findByNameAndUserId(book);
		if(nbook!=null){
			result.setStatus(1);
			result.setMsg("笔记本名称已存在");
			return result;
		}
		
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_type_id("5");  //默认为5
		//设置生成时间：将java中系统时间设置为笔记本的创建时间
		long l = System.currentTimeMillis();  //当前系统时间
		Timestamp time = new Timestamp(l);  //构造方法中需要传入long值
		book.setCn_notebook_createtime(time);
		bookDao.save(book);
		
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		//页面列表的笔记本：需要bookId，和bookName。bookName可以从页面获得
		result.setData(bookId);//将新建笔记本的bookId返回  
		
		return result;
	}



	//删除笔记本
	public NoteResult delete(String bookId) {
		NoteResult result = new NoteResult();
		//检测该笔记本是否有笔记
		int rows = noteDao.countByBookId(bookId);
		if(rows > 0){//如果该笔记本有笔记，不允许删除该笔记本
			result.setStatus(1);
			result.setMsg("需要先清除笔记才能删除笔记本");
			
			return result;
		}
		
		//调用dao，根据bookId删除笔记本
		bookDao.delete(bookId);
		result.setStatus(0);
		result.setMsg("删除笔记本成功");
		return result;
	}



	//更新笔记本标题
	public NoteResult updateBookName(String userId, 
			  String bookId, String bookName) {
		
		NoteResult result = new NoteResult();
		
		NoteBook book = new NoteBook();
		book.setCn_user_id(userId);
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		
		//检查是否重名
		NoteBook book_temp = bookDao.findByNameAndUserId(book);
		if(book_temp != null){
			result.setStatus(1);
			result.setMsg("笔记本名称已存在");
			return result;
		}
		
		//更新操作
		bookDao.updateName(book);
		result.setStatus(0);
		result.setMsg("更新笔记本名称成功");
		return result;
	}

}
