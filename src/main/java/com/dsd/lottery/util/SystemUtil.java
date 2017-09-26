package com.dsd.lottery.util;

import java.io.File;

/**
 * 系统工具�?
 * 
 * @author acer-pc
 *
 */
public class SystemUtil {

	/**
	 * 项目跟路�?
	 */
	public static final String PROJECT_PATH = System.getProperty("user.dir");

	/**
	 * 主要资源文件路径
	 */
	public static final String MAIN_RESOURCE = "src" + File.separator + "main"
			+ File.separator + "resources";

	/**
	 * 测试资源文件
	 */
	public static final String TEST_RESOURCE = "src" + File.separator + "test"
			+ File.separator + "resources";

	public static final String MAIN_RESOURCE_PATH = PROJECT_PATH
			+ File.separator + MAIN_RESOURCE;

	public static final String TEST_RESOURCE_PATH = PROJECT_PATH
			+ File.separator + TEST_RESOURCE;

	public static final String MAIN_RESOURCE_CONFIG = MAIN_RESOURCE_PATH
			+ File.separator + "config";
	
	public static final String TEST_RESOURCE_CONFIG = TEST_RESOURCE_PATH
			+ File.separator + "config";
}
