package com.dsd.lottery.forecast.consumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.enums.EnumQueryStatus;
import com.dsd.lottery.forecast.model.QueryResult;
import com.dsd.lottery.model.GroupData;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;
import com.dsd.lottery.service.LotteryForecastService;
import com.dsd.lottery.util.DigitUtil;
import com.dsd.lottery.util.ResourceUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 消费者
 * @author daishengda
 *
 */
public class ConsumerThread implements Runnable {
	
	/**消费者名称**/
	private String name;
	
	/** 仓库**/
	private StorageModel<QueryResult> storage;
	
	/** 插入**/
	private MyBatisDAO batisDAO;
	
	private List<GroupData> groupList;
	
	private Map<String,List<QueryResult>> result;
	
	private Map<String,EnumQueryStatus> statusMap;
	
	public ConsumerThread(String name, StorageModel<QueryResult> storage, MyBatisDAO batisDAO, List<GroupData> groupList) {
		this.name = name;
		this.storage = storage;
		this.batisDAO = batisDAO;
		this.groupList = groupList;
		this.result = new HashMap<String,List<QueryResult>>();
		this.statusMap = new HashMap<String, EnumQueryStatus>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StorageModel<QueryResult> getStorage() {
		return storage;
	}

	public void setStorage(StorageModel<QueryResult> storage) {
		this.storage = storage;
	}

	public MyBatisDAO getBatisDAO() {
		return batisDAO;
	}

	public void setBatisDAO(MyBatisDAO batisDAO) {
		this.batisDAO = batisDAO;
	}

	@Override
	public void run() {
		boolean flag = true;
		int i = 0;
		Integer limit = null;
		while(flag){
			//生产者停产，已消费完成
			if(storage.isComplete())
			{
				flag = false;
				addGroupList(limit);
				assemble();
				break;
			}
			try {
				if(!storage.isEmpty())
				{
					QueryResult result = storage.pop();
					if(limit == null)
					{
						limit = result.getStage()+Integer.valueOf(LotteryForecastService.BEHIND_STAGE);
					}
					this.constructData(result);
				}
			} catch (InterruptedException e) {
				LogUtil.error(name+"消费者消费失败!", e);
			}
			//每五组做一次查询s
			if(i++==LotteryConst.QUERY_LOOP_NUMBER)
			{
				addGroupList(limit);
				i = 0;
			}
		}
	}

	/**
	 * 组装数据
	 */
	private void assemble() {
		List<LotteryModel> lotteryList;
		StringBuilder groupDesc;
		String desc;
		for(GroupData data:groupList)
		{
			lotteryList = data.getList();
			if(lotteryList !=null && !lotteryList.isEmpty())
			{
				List<QueryResult> list = result.get(lotteryList.get(0).getNumber());
				groupDesc = new StringBuilder();
				for(QueryResult query : list)
				{
					desc = ResourceUtil.format(LotteryConst.GROUP_FORMAT,
							query.getLeft(), query.getRight());
					groupDesc.append(desc+" ");
				}
				for(LotteryModel model : lotteryList)
				{
					model.setGroupDesc(groupDesc.toString());
				}
			}
		}
		Collections.sort(groupList,new Comparator<GroupData>() {

			@Override
			public int compare(GroupData o1, GroupData o2) {
				LotteryModel o1Model = o1.getList().get(0);
				LotteryModel o2Model = o2.getList().get(0);
				return Long.compare(o1Model.getSortid(),o2Model.getSortid());
			}
			
		});
	}

	private void addGroupList(Integer limit) {
		Map<String,Object> map = new HashMap<String, Object>();
		for(Entry<String, EnumQueryStatus> entry : statusMap.entrySet())
		{
			if(EnumQueryStatus.READY == entry.getValue())
			{
				map.put("sortid", DigitUtil.convertNumber(entry.getKey()));
				map.put("limit", limit);
				List<LotteryModel> list = batisDAO.selectList("LotteryRecordMapper.queryRecordLimit", map);
				groupList.add(new GroupData(list));
				statusMap.put(entry.getKey(), EnumQueryStatus.FINISH);
			}
		}
	}
	
	private void constructData(QueryResult queryResult)
	{
		String code = queryResult.getCode();
		List<QueryResult> list = result.get(code);
		EnumQueryStatus status = statusMap.get(code);
		if(list == null)
		{
			list = new ArrayList<QueryResult>();
			result.put(code, list);
		}
		if(status == null)
		{
			statusMap.put(code, EnumQueryStatus.READY);
		}
		list.add(queryResult);
	}
}
