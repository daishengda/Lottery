package com.dsd.lottery.db;


/**
 * 数据源环境变量,线程安全
 * @author daishengda
 *
 */
public class EnvCache {

	private static final ThreadLocal<String> envCache = new ThreadLocal<String>(); 
	
	public static void setCurEnv(String curEnv) {  
		envCache.set(curEnv);  
    }  
	
	public static String getCurEnv() {  
        return envCache.get();  
    }  
  
    public static void clearDbType() {  
    	envCache.remove();  
    } 
}
