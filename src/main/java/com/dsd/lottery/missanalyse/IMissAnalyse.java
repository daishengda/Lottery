package com.dsd.lottery.missanalyse;

/**
 * 遗漏分析接口
 * @author daishengda
 *
 */
public interface IMissAnalyse {

	/**
	 * 创建遗漏组合数据
	 */
	void createMissGroup();
	
	/**
	 * 删除遗漏组合数据
	 */
	boolean deleteMissGroup();
	
	/**
	 * 保存遗漏分析结果
	 */
	void saveMissResult();
}
