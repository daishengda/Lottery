package com.dsd.lottery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 文件工具类
 * 
 * @author daishengda
 *
 */
@Service("lotterySplitService")
public class LotterySplitService {

	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	/**
	 * 动态创建表
	 * @param ids
	 * @return
	 */
	public boolean createTable(String combid)
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("tableName", String.format(LotteryConst.SPLIT_TABLE_NAME,combid));
		return myBatisDAO.insert("LotterySplitMapper.createSplitTable", param);
	}
	
	/**
	 * 动态创建表
	 * @param ids
	 * @return
	 */
	public boolean createTable(List<String> combids)
	{
		boolean flag = true;
		for(String combid : combids)
		{
			if(!createTable(combid))
			{
				flag = false;
				LogUtil.error("create table faied!"+String.format(LotteryConst.SPLIT_TABLE_NAME,combid));
			}
		}
		return flag;
	}
}
