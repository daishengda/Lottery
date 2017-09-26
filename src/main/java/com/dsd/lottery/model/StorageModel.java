package com.dsd.lottery.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 仓库，用来存放产品(生产消费模式)
 * @author daishengda
 *
 */
public class StorageModel<T> {

	/**
	 * 产品仓库
	 */
	private BlockingQueue<T> queues = new LinkedBlockingQueue<T>();
	
	/**
	 * 是否停产
	 */
	private boolean isStop = false;
	
	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	
	/**
	 * 判断生产者是否停产，并且消费者已消费完成
	 * @return
	 */
	public boolean isComplete(){
		return isStop && queues.isEmpty();
	}
	
	public boolean isEmpty()
	{
		return queues.isEmpty();
	}

	/**
	 * 生产
	 * @param t
	 * @throws InterruptedException
	 */
	public void push(T t) throws InterruptedException{
		queues.put(t);
	}
	
	/**
	 * 消费
	 * @return
	 * @throws InterruptedException
	 */
	public T pop() throws InterruptedException{
		return queues.take();
	}
	
	/**
	 * 消费超时使用
	 * @return
	 * @throws InterruptedException
	 */
	public T pop(long timeout, TimeUnit unit) throws InterruptedException{
		return queues.poll(timeout, unit);
	}
}
