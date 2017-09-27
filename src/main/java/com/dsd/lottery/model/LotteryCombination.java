package com.dsd.lottery.model;

import com.dsd.lottery.enums.EnumStatus;

/**
 * 252组合模型
 * @author daishengda
 *
 */
public class LotteryCombination {

	private int id;
	
	/**左边号码(小)**/
	private String left;
	
	/**右边号码(大)**/
	private String right;
	
	/**组合拆分状态**/
	private int status = EnumStatus.READY.getStatus();

	public LotteryCombination(String left, String right) {
		this.left = left;
		this.right = right;
	}
	
	public LotteryCombination(String left, String right, int status) {
		this.left = left;
		this.right = right;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LotteryCombination() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "LotteryCombination [id=" + id + ", left=" + left + ", right="
				+ right + ", status=" + status + "]";
	}
}
