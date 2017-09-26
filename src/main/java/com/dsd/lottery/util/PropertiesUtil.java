package com.dsd.lottery.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 操作properties工具
 * @author acer-pc
 *
 */
public class PropertiesUtil {

	
	public static Properties load(String path)
	{
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			properties.load(in);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		finally
		{
			if(null != in)
			{
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return properties;
	}
	
	public static void write(String filePath,String key,String value)
	{
		Properties properties = new Properties();
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			properties.setProperty(key, value);
			properties.store(out, key);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		finally
		{
			if(null!=out)
			{
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		
	}
}
