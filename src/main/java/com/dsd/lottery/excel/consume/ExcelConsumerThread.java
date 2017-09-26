package com.dsd.lottery.excel.consume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.excel.model.ProductModel;
import com.dsd.lottery.excel.util.ExcelCreate;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 
 * @author daishengda
 *
 */
public class ExcelConsumerThread implements Runnable {

	private BlockingQueue<ProductModel> queue;

	private AtomicInteger atomic;

	private CountDownLatch latch;

	private String path;
	
	private String toPath;

	public ExcelConsumerThread(BlockingQueue<ProductModel> queue,
			AtomicInteger atomic, CountDownLatch latch, String path,String toPath) {
		this.queue = queue;
		this.atomic = atomic;
		this.latch = latch;
		this.path = path;
		this.toPath = toPath;
	}

	@Override
	public void run() {
		ExcelCreate excelParse = null;
		try {
			excelParse = new ExcelCreate(path, toPath);
			ProductModel productModel;
			while (true) {
				if (atomic.get() == 0 && queue.peek() == null) {
					break;
				}
				productModel = queue.poll(1, TimeUnit.SECONDS);
				if (productModel != null) {
					excelParse.createSheet(productModel.getSheetName(),
							productModel.getExcelData());
				}
			}
			excelParse.write();
		} catch (InterruptedException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID,
					"queue poll is fail", e);
		} finally {
			latch.countDown();
			excelParse.close();
		}
	}
}
