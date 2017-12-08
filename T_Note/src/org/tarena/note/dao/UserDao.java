package org.tarena.note.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tarena.note.entity.User;

/**
 * User接口：
 * 		与UserMapper.xml文件对应
 * @author 全文超
 * 2016-05-08 10:21:22
 *
 */
@Repository
public interface UserDao {
	User findByName(String name);
	int save(User user);
	int updateToken(Map<String, Object> param);
}
