package com.dsd.lottery.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import com.dsd.lottery.util.log.LogUtil;

public class CommonProperties {

	private Properties properties;
	
	private static final CommonProperties ws = new CommonProperties();
	
	private CommonProperties()
	{
		String path = this.getClass().getResource("/").getPath()+File.separator+"config.properties";
		try {
			path = URLDecoder.decode(path,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LogUtil.error("解码错误！", e);
		}
		LogUtil.info("路径="+path);
		properties = PropertiesUtil.load(path);
	}
	
	public static CommonProperties getIntance()
	{
		return ws;
	}
	
	public String getValue(String key)
	{
		return properties.getProperty(key);
	}
	
	public static String getCommonValue(String key)
	{
		return ws.getValue(key);
	}
	
	public static String getServiceName()
	{
		return ws.getValue("service_name");
	}
}
