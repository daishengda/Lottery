package com.dsd.lottery.enums;

/**
 * 遗漏算法组合
 * 
 * @author daishengda
 *
 */
public enum EnumMissGroup {

	TWO_GROUP(2),

	THREE_GROUP(3),

	FOUR_GROUP(4),

	FIVE_GROUP(5);

	private int digit;

	private EnumMissGroup(int digit) {
		this.digit = digit;
	}

	public int getDigit() {
		return digit;
	}
}
