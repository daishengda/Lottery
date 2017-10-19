package com.dsd.lottery.model.miss;

/**
 * 遗漏算法结果
 * 
 * @author daishengda
 *
 */
public class MissResultModel {

	/** id **/
	private long id;

	private long begin;

	private long end;

	private int missPeriods;

	private int type;

	private int digit;

	public MissResultModel() {

	}

	public MissResultModel(long begin, long end, int missPeriods, int type,
			int digit) {
		this.begin = begin;
		this.end = end;
		this.missPeriods = missPeriods;
		this.type = type;
		this.digit = digit;
	}

	public MissResultModel(long id, long begin, long end, int missPeriods,
			int type, int digit) {
		this(begin, end, missPeriods, type, digit);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBegin() {
		return begin;
	}

	public void setBegin(long begin) {
		this.begin = begin;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public int getMissPeriods() {
		return missPeriods;
	}

	public void setMissPeriods(int missPeriods) {
		this.missPeriods = missPeriods;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	@Override
	public String toString() {
		return "MissResultModel [id=" + id + ", begin=" + begin + ", end="
				+ end + ", missPeriods=" + missPeriods + ", type=" + type
				+ ", digit=" + digit + "]";
	}
}
