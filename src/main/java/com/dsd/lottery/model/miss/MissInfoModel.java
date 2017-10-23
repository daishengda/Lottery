package com.dsd.lottery.model.miss;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前台的遗漏算法信息
 * @author daishengda
 *
 */
public class MissInfoModel {

	private String group;
	
	private int digit;
	
	private List<MissModel> missList = new ArrayList<MissModel>();
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public List<MissModel> getMissList() {
		return missList;
	}

	public void setMissList(List<MissModel> missList) {
		this.missList = missList;
	}

	public static class MissModel{
		
		private long begin;
		
		private long end;
		
		private int type;
		
		private String desc;
		
		private int missPeriods;

		public MissModel(long begin, long end, int type, String desc,
				int missPeriods) {
			this.begin = begin;
			this.end = end;
			this.type = type;
			this.desc = desc;
			this.missPeriods = missPeriods;
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

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public int getMissPeriods() {
			return missPeriods;
		}

		public void setMissPeriods(int missPeriods) {
			this.missPeriods = missPeriods;
		}
	}
}
