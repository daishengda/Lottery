package com.dsd.lottery.enums;

/**
 * Ë³Ðò
 * @author daishengda
 *
 */
public enum EnumSort {

	/**
	 * ÕýÐò
	 */
	ASC_SORT("asc"),
	
	/**
	 *·´Ðò
	 */
	DESC_SORT("desc");
	
	private String sort;

	private EnumSort(String sort) {
		this.sort = sort;
	}

	public String getSort() {
		return sort;
	}
}
