package com.dsd.lottery.model.miss;

/**
 * 遗漏算法结果
 * @author daishengda
 *
 */
public class MissResultModel {

	private int id;
	
	private String begin;
	
	private String end;
	
	private int missPeriods;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getMissPeriods() {
		return missPeriods;
	}

	public void setMissPeriods(int missPeriods) {
		this.missPeriods = missPeriods;
	}

	@Override
	public String toString() {
		return "MissResultModel [id=" + id + ", begin=" + begin + ", end="
				+ end + ", missPeriods=" + missPeriods + "]";
	}
}
