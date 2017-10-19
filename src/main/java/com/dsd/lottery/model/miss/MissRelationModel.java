package com.dsd.lottery.model.miss;

/**
 * 组合与结果关联表
 * @author daishengda
 *
 */
public class MissRelationModel {

	/**组合ID**/
	private int groupId;
	
	/** 结果ID**/
	private long resultId;

	public MissRelationModel() {
	}

	public MissRelationModel(int groupId, long resultId) {
		this.groupId = groupId;
		this.resultId = resultId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public long getResultId() {
		return resultId;
	}

	public void setResultId(long resultId) {
		this.resultId = resultId;
	}
}
