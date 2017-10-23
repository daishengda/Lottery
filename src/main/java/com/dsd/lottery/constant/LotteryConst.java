package com.dsd.lottery.constant;

/**
 *常量
 * @author daishengda
 *
 */
public class LotteryConst {

	/** 从个位到万位分割表,后面是tbl_lottery_combination对应的主键ID，分表**/
	public static final String SPLIT_TABLE_NAME = "tbl_lottery_split_%s";
	
	public static final String DEFAULT_CHARTSET_NAME = "UTF-8";
	
	/**上传文件分割符**/
	public static final String SEPARATOR = "\t";
	
	/**上传文件分割符**/
	public static final String EMTY_SEPARATOR="    ";
	
	/**最大期数**/
	public static final int MAX_STAGE = 40;
	
	/**最小期数**/
	public static final int MIN_STAGE = 20;
	
	/**最大线程数**/
	public static final int MAX_THREAD_NUM = 10;
	
	/**彩票位数**/
	public static final int LOTTERY_STAGE_NUM = 5;
	
	/**小**/
	public static final int SMALL_NUM = 0;
	
	/**大**/
	public static final int LARGE_NUM = 1;
	
	/**仓库最大容量,252 * 25000**/
	public static final int MAX_STORAGE_CAPACITY = 5040000;
	
	/**每252*2500条消费者角色插入一次数据**/
	public static final int CONSUMER_INSERT_NUM = 100000;
	
	/**经测试，每次最大允许插入4000条数据**/
	public static final int MAX_ALLOW_INSERT = 10000;
	
	/**插入表格sql的列头**/
	public static final String SPLIT_TABLE_HEAD = "insert into tbl_lottery_split_%s(`single_digit`,`tens_digit`,`hundreds_digit`,`thousands_digit`,`tenthousand_digt`,`start_number`) values";

	public static final String SPLIT_TABLE_END = "('%s','%s','%s','%s','%s','%s')";
	
	/**循环几次查一次库**/
	public static final int QUERY_LOOP_NUMBER = 5;
	
	/** 所属组合描述格式***/
	public static final String GROUP_FORMAT = "{0}-{1}";
	
	/** 对序列号number的字符进行替换***/
	public static final String REPLACE_CHARACTER = "-";
	
	/**创建数据库语句**/
	public static final String CREATE_SCHEMA = "CREATE DATABASE IF NOT EXISTS %s;";
	
	/**遗漏表格动态列名(最大遗漏)**/
	public static final String MAX_MISS_COLUMN_NAME = "最大遗漏(期间,{0}直选)";
	
	/**遗漏表格动态列名(本期遗漏)**/
	public static final String CURRENT_MISS_COLUMN_NAME = "本期遗漏(期间,{0}直选)";

}
