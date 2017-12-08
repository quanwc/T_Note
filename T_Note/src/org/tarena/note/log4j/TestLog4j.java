package org.tarena.note.log4j;

import org.apache.log4j.Logger;

/**
 * Log4j工具:
 * 		日志工具
 * 需要增加配置文件：
 * 		默认名为：log4j.properties
 * @author 全文超
 * 2016-05-23 17:14:04
 *
 */
public class TestLog4j {
	
	static Logger logger  = Logger.getLogger(TestLog4j.class);
	
	public static void main(String[] args) {
		logger.debug("调试信息");
		logger.info("普通消息");
		logger.warn("警告信息");
		logger.error("错误信息");
		logger.fatal("致命信息");
	}
}
