package com.dsd.lottery.model;

/**
 * 个位到万位列拆分表(作为生产消费的产品)
 * @author daishengda
 *
 */
public class LotterySplit {

	private int id;
	
	/**'个位列拆分'**/
	private String singleDigit;
	
	/**'十位列拆分'**/
	private String tensDigit;
	
	/**'百位列拆分'**/
	private String hundredsDigit;
	
	/**'千位列拆分'**/
	private String thousandsDigit;
	
	/**'万位列拆分'**/
	private String tenthousandDigt;
	
	/** 开始序列号**/
	private String startNumber;
	
	private int combId;
	

	public LotterySplit(String singleDigit, String tensDigit,
			String hundredsDigit, String thousandsDigit,
			String tenthousandDigt, String startNumber, int combId) {
		this.singleDigit = singleDigit;
		this.tensDigit = tensDigit;
		this.hundredsDigit = hundredsDigit;
		this.thousandsDigit = thousandsDigit;
		this.tenthousandDigt = tenthousandDigt;
		this.startNumber = startNumber;
		this.combId = combId;
	}
	
	public LotterySplit(LotterySplit split)
	{
		this.singleDigit = split.getSingleDigit();
		this.tensDigit = split.getTensDigit();
		this.hundredsDigit = split.getHundredsDigit();
		this.thousandsDigit = split.getThousandsDigit();
		this.tenthousandDigt = split.getTenthousandDigt();
		this.startNumber = split.getStartNumber();
		this.combId = split.getCombId();
	}

	public LotterySplit() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSingleDigit() {
		return singleDigit;
	}

	public void setSingleDigit(String singleDigit) {
		this.singleDigit = singleDigit;
	}

	public String getTensDigit() {
		return tensDigit;
	}

	public void setTensDigit(String tensDigit) {
		this.tensDigit = tensDigit;
	}

	public String getHundredsDigit() {
		return hundredsDigit;
	}

	public void setHundredsDigit(String hundredsDigit) {
		this.hundredsDigit = hundredsDigit;
	}

	public String getThousandsDigit() {
		return thousandsDigit;
	}

	public void setThousandsDigit(String thousandsDigit) {
		this.thousandsDigit = thousandsDigit;
	}

	public String getTenthousandDigt() {
		return tenthousandDigt;
	}

	public void setTenthousandDigt(String tenthousandDigt) {
		this.tenthousandDigt = tenthousandDigt;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public int getCombId() {
		return combId;
	}

	public void setCombId(int combId) {
		this.combId = combId;
	}
}
