package com.dsd.lottery.strategy;

import java.util.List;

import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;

/**
 * 抽象策略角色
 * @author daishengda
 *
 */
public interface IStrategy {

	/**
	 * 策略接口
	 */
	void operate(List<LotteryModel> lotteryList,StorageModel storage,char[][] charArrays,String left,String right,int stage);
}
