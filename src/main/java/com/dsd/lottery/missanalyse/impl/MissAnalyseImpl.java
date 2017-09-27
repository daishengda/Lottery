package com.dsd.lottery.missanalyse.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.enums.EnumMissGroup;
import com.dsd.lottery.missanalyse.IMissAnalyse;
import com.dsd.lottery.model.LotteryCombination;
import com.dsd.lottery.model.miss.MissGroupModel;
import com.dsd.lottery.util.AlgorithmUtil;

/**
 * 遗漏解析算法实现类
 * 
 * @author daishengda
 *
 */
@Service("missAnalyse")
public class MissAnalyseImpl implements IMissAnalyse {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public void createMissGroup() {
		List<String> dataList = Arrays.asList("0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9");
		EnumMissGroup[] enumArray = EnumMissGroup.values();
		List<MissGroupModel> groupList = new ArrayList<MissGroupModel>();
		List<LotteryCombination> cmbList;
		MissGroupModel group;
		for (EnumMissGroup enumMiss : enumArray) {
			cmbList = new AlgorithmUtil().combinationSelect(dataList,
					enumMiss.getDigit());
			for (LotteryCombination cmb : cmbList) {
				group = new MissGroupModel(enumMiss.getDigit(), cmb.getLeft());
				groupList.add(group);
			}
		}
		myBatisDAO.insert("LotteryMissMapper.insertBatchMissGroup", groupList);
	}

	@Override
	public boolean deleteMissGroup() {
		int code = myBatisDAO.delete("LotteryMissMapper.deleteMissGroup");
		return MyBatisDAO.ERROR_CODE != code;
	}

}
