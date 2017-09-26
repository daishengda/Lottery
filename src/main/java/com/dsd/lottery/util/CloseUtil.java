package com.dsd.lottery.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * io关闭
 * @author daishengda
 *
 */
public class CloseUtil {

	public static void close(Closeable closeable)
	{
		if(null != closeable)
		{
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Closeable... closeables)
	{
		if(closeables != null && closeables.length > 0)
		{
			for(Closeable closeable : closeables)
			{
				close(closeable);
			}
		}
	}
	
	public static void closeStream(Closeable closeable)
	{
		if(null != closeable)
		{
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeReader(Closeable closeable)
	{
		if(null != closeable)
		{
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeWriter(Closeable closeable)
	{
		if(null != closeable)
		{
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
