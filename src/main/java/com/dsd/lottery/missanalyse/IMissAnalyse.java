package com.dsd.lottery.missanalyse;

import java.util.List;
import java.util.Map;

import com.dsd.lottery.model.miss.MissInfoModel;

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
	
	/***
	 * 查询遗漏算法信息
	 * @param params
	 * @return
	 */
	List<MissInfoModel> queryMissInfo(Map<String, String> param);
}
