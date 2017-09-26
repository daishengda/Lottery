package com.dsd.lottery.model;

/**
 * 根据最新期数拆分模糊匹配出开始期，和位数
 * @author daishengda
 *
 */
public class StageDigitResult {

	/**
	 * 位数(0个位、1十位、2百分、3千位、4万位)
	 */
	private int digit;
	
	/**
	 * 开始期
	 */
	private String startNumber;

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}
}
