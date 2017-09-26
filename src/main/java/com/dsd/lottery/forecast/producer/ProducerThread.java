package com.dsd.lottery.forecast.producer;

import java.util.List;

import com.dsd.lottery.forecast.model.QueryResult;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;
import com.dsd.lottery.strategy.IStrategy;
import com.dsd.lottery.strategy.impl.CombinationStrategy;

/**
 * 生产者
 * 
 * @author daishengda
 *
 */
public class ProducerThread implements Runnable {
	private StorageModel<QueryResult> storage;
	
	private String left;
	
	private String right;

	private List<LotteryModel> lotteryList;
	
	private IStrategy iStrategy;
	
	private char[][] charArrays;
	
	private int stage;
	
	public ProducerThread(StorageModel<QueryResult> storage,String left,String right,List<LotteryModel> lotteryList,char[][] charArrays, int stage) {
		this.storage = storage;
		this.lotteryList = lotteryList;
		this.charArrays = charArrays;
		this.left = left;
		this.right = right;
		this.stage = stage;
		iStrategy = new CombinationStrategy();
	}

	@Override
	public void run() {
		//启三个消费者线程池
		iStrategy.operate(lotteryList,storage, charArrays, left,right, stage);
	}
}
