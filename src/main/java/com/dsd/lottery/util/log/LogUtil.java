package com.dsd.lottery.util.log;

/**
 * 日志工具，对日志工厂的常用调�?
 * @author daishengda
 *
 */
public class LogUtil {

	public static void debug(String message)
	{
		LogFactory.record(LogType.DEBUG, message);
	}
	
	public static void debug(String projectId,String message)
	{
		LogFactory.record(LogType.DEBUG, projectId, message);
	}
	
	public static void debug(String message,Throwable e)
	{
		LogFactory.record(LogType.DEBUG, message,e);
	}
	
	public static void debug(String projectId,String message,Throwable e)
	{
		LogFactory.record(LogType.DEBUG, projectId, message,e);
	}
	
	public static void info(String message)
	{
		LogFactory.record(LogType.INFO, message);
	}
	
	public static void info(String projectId,String message)
	{
		LogFactory.record(LogType.INFO, projectId, message);
	}
	
	public static void info(String message,Throwable e)
	{
		LogFactory.record(LogType.INFO, message,e);
	}
	
	public static void info(String projectId,String message,Throwable e)
	{
		LogFactory.record(LogType.INFO, projectId, message,e);
	}
	
	public static void warn(String message)
	{
		LogFactory.record(LogType.WARN, message);
	}
	
	public static void warn(String projectId,String message)
	{
		LogFactory.record(LogType.WARN, projectId, message);
	}
	
	public static void warn(String message,Throwable e)
	{
		LogFactory.record(LogType.WARN, message,e);
	}
	
	public static void warn(String projectId,String message,Throwable e)
	{
		LogFactory.record(LogType.WARN, projectId, message,e);
	}
	
	public static void error(String message)
	{
		LogFactory.record(LogType.ERROR, message);
	}
	
	public static void error(String projectId,String message)
	{
		LogFactory.record(LogType.ERROR, projectId, message);
	}
	
	public static void error(String message,Throwable e)
	{
		LogFactory.record(LogType.ERROR, message,e);
	}
	
	public static void error(String projectId,String message,Throwable e)
	{
		LogFactory.record(LogType.ERROR, projectId, message,e);
	}
	
	public static void fatal(String message)
	{
		LogFactory.record(LogType.FATAL, message);
	}
	
	public static void fatal(String projectId,String message)
	{
		LogFactory.record(LogType.FATAL, projectId, message);
	}
	
	public static void fatal(String projectId,String message,Throwable e)
	{
		LogFactory.record(LogType.FATAL, projectId, message,e);
	}
}
