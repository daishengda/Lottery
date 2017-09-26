package com.dsd.lottery.forecast.model;


/**
 * 查询结果
 * @author daishengda
 *
 */
public class QueryResult {

	private String code;
	
	private String left;
	
	private String right;
	
	private Integer digit;
	
	private Integer stage;
	
	public QueryResult(String code, String left, String right,Integer digit, Integer stage) {
		this.code = code;
		this.left = left;
		this.right = right;
		this.digit = digit;
		this.stage = stage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public Integer getDigit() {
		return digit;
	}

	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}
}
