package com.dsd.lottery.excel.strategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.dsd.lottery.constant.PropertiesConst;
import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.excel.consume.ExcelConsumerThread;
import com.dsd.lottery.excel.model.ProductModel;
import com.dsd.lottery.excel.product.ExcelProducerThread;
import com.dsd.lottery.excel.util.ExcelParse;
import com.dsd.lottery.util.CommonProperties;
import com.dsd.lottery.util.DateUtil;
import com.dsd.lottery.util.ResourceUtil;

/**
 * 实现类
 * 
 * @author daishengda
 *
 */
public class ExcelOperationImpl implements IExcelOperation {

	/**
	 * 路径
	 */
	private String path;

	private int diffStage;

	public ExcelOperationImpl(String path, int diffStage) {
		this.path = path;
		this.diffStage = diffStage;
	}

	@Override
	public Map<String, String[][]> parse() {
		int[][] tenComb = ExcelConstant.FIVE_SELECT_THREE;
		ExcelParse excelParse = new ExcelParse(path);
		List<List<String>> sheetList = excelParse.getSheetData(
				ExcelConstant.SHEET_INDEX, 0, 0);
		excelParse.close();
		Map<String, String[][]> resultMap = new LinkedHashMap<String, String[][]>();
		String[][] arrays;
		StringBuilder sb = new StringBuilder();
		int iLength = sheetList.size();
		int jLength;
		int i = 0, j;
		for (List<String> sheetData : sheetList) {
			jLength = sheetData.size();
			j = 0;
			for (String str : sheetData) {
				for (int[] tenArrays : tenComb) {
					sb.setLength(0);
					String keyString = ResourceUtil.convertDigit(tenArrays);
					for (int value : tenArrays) {
						sb.append(str.charAt(value));
					}
					arrays = resultMap.get(keyString);
					if (arrays == null) {
						arrays = new String[iLength][jLength];
						resultMap.put(keyString, arrays);
					}
					arrays[i][j] = sb.toString();
				}
				j++;
			}
			i++;
		}
		return resultMap;
	}

	@Override
	public String create(Map<String, String[][]> dataMap) {
		final String MAX_EXCEL_THREAD_NUM = CommonProperties.getCommonValue(PropertiesConst.MAX_EXCEL_THREAD_NUM);
		int threadNum = StringUtils.isNotEmpty(MAX_EXCEL_THREAD_NUM) ? Integer.parseInt(MAX_EXCEL_THREAD_NUM):3;
		ExecutorService threadPools = Executors.newFixedThreadPool(threadNum);
		BlockingQueue<ProductModel> queue = new LinkedBlockingQueue<ProductModel>();
		AtomicInteger atomic = new AtomicInteger(dataMap.size());
		for (Entry<String, String[][]> entry : dataMap.entrySet()) {
			threadPools.execute(new Thread(new ExcelProducerThread(entry
					.getValue(), entry.getKey(), queue, atomic, diffStage)));
		}
		threadPools.shutdown();
		CountDownLatch latch = new CountDownLatch(1);
		String fileName = DateUtil.timestampToString(
				System.currentTimeMillis(), "yyyy-MM-dd_HH-mm-ss")+"_"+diffStage+"期";
		String toPath = getGeneratePath(fileName);
		new Thread(new ExcelConsumerThread(queue, atomic, latch, path, toPath))
				.start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return toPath;
	}

	/**
	 * 获取生成路径
	 * 
	 * @return
	 */
	private String getGeneratePath(String fileName) {
		StringBuilder path = new StringBuilder();
		path.append(ExcelConstant.GENERATE_PATH);
		path.append(fileName + ".xlsx");
		String generatePath = path.toString();
		return generatePath;
	}
}
