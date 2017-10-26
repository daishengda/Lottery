package com.dsd.lottery.upgrade;

/**
 * 数据库升级接口
 * @author daishengda
 *
 */
public interface IUpgradeService {

	/**
	 * 创建数据库
	 * @return
	 */
	boolean createSchema();
	
	/**
	 * 初始化数据库
	 * @return
	 */
	boolean initDatabase();
}
