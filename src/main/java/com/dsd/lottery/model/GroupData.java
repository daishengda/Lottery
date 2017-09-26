package com.dsd.lottery.model;

import java.util.List;

/**
 * 每个组合就是一个list
 * @author daishengda
 *
 */
public class GroupData {

	private List<LotteryModel> list;
	
	public GroupData(List<LotteryModel> list) {
		this.list = list;
	}

	public List<LotteryModel> getList() {
		return list;
	}

	public void setList(List<LotteryModel> list) {
		this.list = list;
	}
}

