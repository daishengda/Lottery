package com.dsd.lottery.model.miss;

/**
 * 遗漏组合模型
 * @author daishengda
 *
 */
public class MissGroupModel {

	private int id;
	
	private int digit;
	
	private String group;

	public MissGroupModel() {
	}

	public MissGroupModel(int digit, String group) {
		this.digit = digit;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "MissGroupModel [id=" + id + ", digit=" + digit + ", group="
				+ group + "]";
	}
}
