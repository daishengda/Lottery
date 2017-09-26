package com.dsd.lottery.db;
/**
 * 直接执行的sql语句适配
 * @author acer-pc
 *
 */
public class SQLAdapter {
	String sql;

	public SQLAdapter(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
