package com.dsd.lottery.missanalyse.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

import com.dsd.lottery.constant.PropertiesConst;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.miss.MissGroupModel;
import com.dsd.lottery.util.CommonProperties;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 主任务
 * 
 * @author daishengda
 *
 */
public class MissMasterTask implements Runnable {

	private MyBatisDAO myBatisDAO;

	/**
	 * tbl_lottery_miss_result结果ID
	 */
	private AtomicLong atomic = new AtomicLong();

	public MissMasterTask(MyBatisDAO myBatisDAO) {
		this.myBatisDAO = myBatisDAO;
	}

	private void doBefore() {
		myBatisDAO.delete("LotteryMissMapper.deleteMissResult");
	}

	@Override
	public void run() {
		doBefore();
		List<MissGroupModel> missGroupList = myBatisDAO
				.selectList("LotteryMissMapper.queryMissGroup");
		List<LotteryModel> lotteryList = myBatisDAO
				.selectList("LotteryRecordMapper.queryRecordLimit");
		try {
			executeTask(missGroupList, lotteryList);
		} catch (InterruptedException e) {
			LogUtil.error("executeTask is faild!", e);;
		}
	}

	private void executeTask(List<MissGroupModel> missGroupList, List<LotteryModel> lotteryList) throws InterruptedException{
		final String MAX_EXCEL_THREAD_NUM = CommonProperties.getCommonValue(PropertiesConst.MAX_MISS_THREAD_NUM);
		int threadNum = StringUtils.isNotEmpty(MAX_EXCEL_THREAD_NUM) ? Integer.parseInt(MAX_EXCEL_THREAD_NUM):2;
		ExecutorService threadPools = Executors.newFixedThreadPool(threadNum);
		for (MissGroupModel missGroup : missGroupList) {
			threadPools.execute(new Thread(new MissChildTask(lotteryList, missGroup,
					atomic, myBatisDAO)));
		}
		threadPools.shutdown();
		while (true) {
			if(threadPools.isTerminated())
			{
				break;
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
