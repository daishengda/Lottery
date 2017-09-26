package com.dsd.lottery.model;

/**
 * 开奖结果
 * @author daishengda
 *
 */
public class LotteryModel {
	
	/**
	 * id
	 */
	private int id;

	/**
	 * 期数
	 */
	private String number;
	
	/**
	 * 开奖号码
	 */
	private String code;
	
	/**
	 * 根据number的数字部分进行合并，方便后面排序
	 */
	private long sortid;
	
	/**
	 * 状态
	 */
	private int status = 0;
	
	/**
	 * 组合描述
	 */
	private String groupDesc;

	public LotteryModel() {
	}
	
	public LotteryModel(int id, String number, String code, long sortid) {
		this.id = id;
		this.number = number;
		this.code = code;
		this.sortid = sortid;
	}

	public LotteryModel(String number, String code, long sortid) {
		this.number = number;
		this.code = code;
		this.sortid = sortid;
	}

	public LotteryModel(String number, String code, int status) {
		this.number = number;
		this.code = code;
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public long getSortid() {
		return sortid;
	}

	public void setSortid(long sortid) {
		this.sortid = sortid;
	}
}
