package com.dsd.lottery.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.model.LotterySplit;

/**
 * 用来存储最新20-40期分析数据，供ConsumerTopThread消费
 * @author daishengda
 *
 */
public class CacheStorageQueue {

	public static BlockingQueue<LotterySplit> queues = new LinkedBlockingQueue<LotterySplit>(LotteryConst.MAX_STORAGE_CAPACITY);
	
	/**
	 * 是否停产
	 */
	private static boolean isStop = false;
	
	private CacheStorageQueue()
	{
		
	}
	public static boolean isStop() {
		return isStop;
	}

	public static void setStop(boolean isStop) {
		CacheStorageQueue.isStop = isStop;
	}
	
	/**
	 * 判断生产者是否停产，并且消费者已消费完成
	 * @return
	 */
	public static boolean isComplete(){
		return isStop && queues.isEmpty();
	}

	/**
	 * 生产
	 * @param split
	 * @throws InterruptedException
	 */
	public static void push(LotterySplit split) throws InterruptedException{
		queues.put(split);
	}
	
	/**
	 * 消费
	 * @return
	 * @throws InterruptedException
	 */
	public static LotterySplit pop() throws InterruptedException{
		return queues.take();
	}
}
