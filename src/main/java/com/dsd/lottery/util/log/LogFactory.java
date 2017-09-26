package com.dsd.lottery.util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * 日志处理工厂
 * 
 * @author daishengda
 *
 */
public class LogFactory {

	/**
	 * 追踪栈深�?
	 */
	private static final int STACK_TRACK_DEPTH = 2;

	/**
	 * 日志信息格式
	 */
	private static final String LOG_FORMAT = "(Class:%s | Method:%s | Line:%d) - %s";
	
	public static void record(LogType logType, String message,Throwable e)
	{
		record(logType, null, message,e);
	}
	
	public static void record(LogType logType, String projectId, String message,Throwable e)
	{
		Logger logger = LogManager.getLogger();
		ThreadContext.put("projectId", projectId);
		String msg = getMessage(message); 
		switch (logType) {
		case DEBUG:
			logger.debug(msg,e);
			break;
			
		case INFO:
			logger.info(msg,e);
			break;
			
		case WARN:
			logger.warn(msg,e);
			break;
		case ERROR:
			logger.error(msg,e);
			break;
			
		case FATAL:
			logger.fatal(msg,e);
			break;
			
		default:
			break;
		}
	}
	
	public static void record(LogType logType, String message)
	{
		record(logType, null, message);
	}

	public static void record(LogType logType, String projectId, String message) {
		Logger logger = LogManager.getLogger();
		ThreadContext.put("projectId", projectId);
		String msg = getMessage(message); 
		switch (logType) {
		case DEBUG:
			logger.debug(msg);
			break;
			
		case INFO:
			logger.info(msg);
			break;
			
		case WARN:
			logger.warn(msg);
			break;
		case ERROR:
			logger.error(msg);
			break;
			
		case FATAL:
			logger.fatal(msg);
			break;
			
		default:
			break;
		}
	}

	private static String getMessage(String info) {
		StackTraceElement[] stackElements = new Throwable().getStackTrace();
		StackTraceElement stackElement = stackElements[STACK_TRACK_DEPTH];
		String className = stackElement.getClassName();
		String methodName = stackElement.getMethodName();
		int lineNumber = stackElement.getLineNumber();
		return String.format(LOG_FORMAT, className, methodName, lineNumber, info);
	}
}
