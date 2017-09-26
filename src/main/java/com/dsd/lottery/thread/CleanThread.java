package com.dsd.lottery.thread;

import com.dsd.lottery.constant.PropertiesConst;
import com.dsd.lottery.util.CommonProperties;
import com.dsd.lottery.util.FileUtil;

public class CleanThread extends Thread {
	
	private static final String CLEAN_TIME = CommonProperties.getCommonValue(PropertiesConst.CLEAN_DATA_TIME);
	
	private String folder;

	public CleanThread(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public void run() {
		long sleepTime = 1000 * Long.parseLong(CLEAN_TIME);
		try {
			Thread.sleep(sleepTime);
			FileUtil.clearFolder(folder);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
