package com.dsd.lottery.missanalyse.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.enums.EnumMissGroup;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.miss.MissGroupModel;
import com.dsd.lottery.model.miss.MissRelationModel;
import com.dsd.lottery.model.miss.MissResultModel;
import com.dsd.lottery.util.DigitUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 遗漏分析任务
 * 
 * @author daishengda
 *
 */
public class MissChildTask implements Runnable {

	/** 彩票所有往期号码 **/
	private List<LotteryModel> lotteryList;

	/** 位数 **/
	private MissGroupModel missGroup;

	/**
	 * tbl_lottery_miss_result结果ID
	 */
	private AtomicLong atomic;

	private MyBatisDAO myBatisDAO;

	public MissChildTask(List<LotteryModel> lotteryList,
			MissGroupModel missGroup, AtomicLong atomic, MyBatisDAO myBatisDAO) {
		this.lotteryList = lotteryList;
		this.missGroup = missGroup;
		this.atomic = atomic;
		this.myBatisDAO = myBatisDAO;
	}

	@Override
	public void run() {
		LogUtil.info(String.format(
				"start calculate MissChildTask!digit = %d,group = %s ",
				missGroup.getDigit(), missGroup.getGroup()));
		List<MissResultModel> missResultList = new ArrayList<MissResultModel>();
		List<MissRelationModel> relationLst = new ArrayList<MissRelationModel>();
		EnumMissGroup enumMiss = EnumMissGroup.getEnumMissGroup(missGroup
				.getDigit());
		for (int type : enumMiss.getMatch()) {
			buildMissResult(missResultList, relationLst, type);
		}
		LogUtil.info(String.format(
				"end calculate MissChildTask!digit = %d,group = %s ",
				missGroup.getDigit(), missGroup.getGroup()));
		afterHandle(missResultList, relationLst);
	}

	private void buildMissResult(List<MissResultModel> missResultList,
			List<MissRelationModel> relationLst, int type) {
		int digit = missGroup.getDigit();
		int[] intArrays = buildIntArrays(type);
		long[] longArrays = buildLongArrays(intArrays);
		long currentId = atomic.incrementAndGet();
		long maxId = atomic.incrementAndGet();
		missResultList.add(new MissResultModel(currentId, longArrays[2],
				0L, intArrays[1], type, digit));
		missResultList.add(new MissResultModel(maxId, longArrays[0],
				longArrays[1], intArrays[0], type, digit));
		relationLst.add(new MissRelationModel(missGroup.getId(), currentId));
		relationLst.add(new MissRelationModel(missGroup.getId(), maxId));
	}

	private int[] buildIntArrays(int type) {
		int size = lotteryList.size();
		// [0] 最大遗漏值,[1]往期当前遗漏值,[2]最大遗漏值出现的索引,[3]往期遗漏值出现的索引
		int[] intArrays = new int[4];
		for (int i = 0; i < size; i++) {
		    boolean isEqual = DigitUtil.isContain(type, missGroup.getGroup(), lotteryList
                    .get(i).getCode());
			if (isEqual) {
				if (intArrays[1] >= intArrays[0]) {
					intArrays[0] = intArrays[1];
					intArrays[2] = i;
				}
				intArrays[1] = 0;
				intArrays[3] = i + 1;
			} else {
				intArrays[1]++;
			}
		}
		return intArrays;
	}

	private long[] buildLongArrays(int[] intArrays) {
		int size = lotteryList.size();
		// [0] 最大遗漏起始期,[1]最大遗漏结束期,[2]本期遗漏起始期,[3]本期遗漏结束期
		long[] longArrays = new long[4];
		if (intArrays[3] >= size) {
			longArrays[2] = -1L;
			longArrays[3] = -1L;
		} else {
			longArrays[2] = lotteryList.get(intArrays[3]).getSortid();
			longArrays[3] = lotteryList.get(size - 1).getSortid();
		}
		if (intArrays[1] >= intArrays[0]) {
			longArrays[0] = longArrays[2];
			longArrays[1] = longArrays[3];
			intArrays[0] = intArrays[1];
		} else {
			longArrays[0] = lotteryList.get(intArrays[2] - intArrays[0])
					.getSortid();
			longArrays[1] = lotteryList.get(intArrays[2] - 1).getSortid();
		}
		return longArrays;
	}

	private void afterHandle(List<MissResultModel> missResultList,
			List<MissRelationModel> relationLst) {
		myBatisDAO.batchHightInsert("LotteryMissMapper.insertBatchMissResult",
				missResultList);
		myBatisDAO.batchHightInsert(
				"LotteryMissMapper.insertBatchMissRelation", relationLst);
	}

}
