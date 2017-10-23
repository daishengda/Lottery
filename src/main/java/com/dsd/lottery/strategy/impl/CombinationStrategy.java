package com.dsd.lottery.strategy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.dsd.lottery.forecast.model.QueryResult;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;
import com.dsd.lottery.strategy.IStrategy;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 252种组合算法
 * @author daishengda
 *
 */
public class CombinationStrategy implements IStrategy {

	@Override
	public void operate(List<LotteryModel> lotteryList,StorageModel<QueryResult> storage, char[][] charArrays,String left,String right,int stage) {
		int size = lotteryList.size();
		try {
			for(int i = 0;i < size;i++)
			{
				if(i + stage < size)
				{
					splitContrast(lotteryList,storage, i , i+stage, charArrays, left, right);
				}
			}
		} catch (InterruptedException e) {
			LogUtil.error("解析数据失败！", e);
		}

	}

	private void splitContrast(List<LotteryModel> lotteryList,StorageModel<QueryResult> storage, int index, int stage,
			char[][] charArrays, String left, String right) throws InterruptedException {
		char codeChar;
		String code;
		int aindex = 0;
		List<Integer> indexList = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
		Boolean[][] arrayBool = {{false,false},{false,false},{false,false},{false,false},{false,false}};
		Iterator<Integer> iterator;
		Integer j;
		int codeIndexOf;
		int charIndexOf;
		for (int i = index;i<stage;i++) {
			code = lotteryList.get(i).getCode();
			iterator = indexList.iterator();
			if(indexList.size()==0)
			{
				return;
			}
			while (iterator.hasNext()) {
				j = iterator.next();
				codeChar = code.charAt(j);
				codeIndexOf = left.indexOf(codeChar);
				charIndexOf = left.indexOf(charArrays[aindex][j]);
				//正方向对比,不相等认为不成立
				if((codeIndexOf>=0 && charIndexOf <0)||
						(codeIndexOf <0 && charIndexOf >= 0))
				{
					arrayBool[j][0] = true;
				}
				
				//反向对比,相等认为不成立
				if((codeIndexOf>=0 && charIndexOf >= 0) ||
					(codeIndexOf<0 && charIndexOf < 0))
				{
					arrayBool[j][1] = true;
				}
				
				if(arrayBool[j][0] && arrayBool[j][1])
				{
					iterator.remove();
				}
			}
			aindex++;
		}
		for(Integer i : indexList)
		{
			storage.push(new QueryResult(lotteryList.get(index).getNumber(), left,right, i, stage - index));
		}
	}
}
