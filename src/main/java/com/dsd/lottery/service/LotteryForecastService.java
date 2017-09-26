package com.dsd.lottery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.constant.PropertiesConst;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.forecast.consumer.ConsumerThread;
import com.dsd.lottery.forecast.model.QueryResult;
import com.dsd.lottery.forecast.producer.ProducerThread;
import com.dsd.lottery.model.GroupData;
import com.dsd.lottery.model.LotteryCombination;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;
import com.dsd.lottery.util.CommonProperties;
import com.dsd.lottery.util.DigitUtil;

/**
 * 文件工具类
 * 
 * @author daishengda
 *
 */
@Service("lotteryService")
public class LotteryForecastService {

	public static final String BEHIND_STAGE = CommonProperties.getCommonValue(PropertiesConst.BEHIND_STAGE);
			
	public static final String FRONT_STAGE = CommonProperties.getCommonValue(PropertiesConst.FRONT_STAGE);
	
	public static final String MAX_THREAD_NUM = CommonProperties.getCommonValue(PropertiesConst.MAX_THREAD_NUM);
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	public List<GroupData> forecastLottery(int stage,String startStage,String endStage) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(startStage))
		{
			params.put("startStage", DigitUtil.convertNumber(startStage));
		}
		if(StringUtils.isNotBlank(endStage))
		{
			params.put("endStage", DigitUtil.convertNumber(endStage));
		}
		params.put("stage", stage);
		List<LotteryModel> lotteryList = myBatisDAO.selectList("LotteryRecordMapper.queryAllRecord", params);
		char[][] charArrays = getTopSplitArray(params);
		int maxThread = Integer.valueOf(MAX_THREAD_NUM);
		//生产者线程池
		ExecutorService cachedProducerThreadPool = Executors.newFixedThreadPool(maxThread);  
		String left;
		StorageModel<QueryResult> storage = new StorageModel<QueryResult>();
		List<LotteryCombination> comList = myBatisDAO.selectList("LotteryCombinationMapper.queryCombination");
		for(LotteryCombination combination : comList)
		{
			left = combination.getLeft();
			cachedProducerThreadPool.execute(new Thread(new ProducerThread(storage,left,combination.getRight(),lotteryList, charArrays, stage)));
		}
		List<GroupData> groupList = new ArrayList<GroupData>();
		Thread thread = new Thread(new ConsumerThread("消费者", storage, myBatisDAO, groupList));
		cachedProducerThreadPool.shutdown();
		thread.start();
		while(true)
		{
			if(cachedProducerThreadPool.isTerminated())
			{
				storage.setStop(true);
				break;
			}
				
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while(true)
		{
			if(!thread.isAlive())
			{
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return groupList;
	}

	private char[][] getTopSplitArray(Map<String,Object> params) {
		List<LotteryModel> recordTopList = myBatisDAO.selectList("LotteryRecordMapper.queryRecordTopSize", params);
		Collections.reverse(recordTopList);
		Integer stage = Integer.valueOf(String.valueOf(params.get("stage")));
		char[][] topChars = new char[stage][LotteryConst.LOTTERY_STAGE_NUM];
		for(int i = 0;i<recordTopList.size();i++)
		{
			String code = recordTopList.get(i).getCode();
			for(int j = 0;j < code.length();j++)
			{
				topChars[i][j] = code.charAt(j);
			}
		}
		return topChars;
	}

}
