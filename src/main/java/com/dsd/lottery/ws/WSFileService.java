package com.dsd.lottery.ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.DBHolder;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.model.GroupData;
import com.dsd.lottery.model.LotteryCombination;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.PageModel;
import com.dsd.lottery.service.LotteryForecastService;
import com.dsd.lottery.service.LotterySplitService;
import com.dsd.lottery.util.AlgorithmUtil;
import com.dsd.lottery.util.CloseUtil;
import com.dsd.lottery.util.DigitUtil;
import com.dsd.lottery.util.ResourceUtil;
import com.dsd.lottery.util.SqlParse;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 
 * @author daishengda
 *
 */
@Path("/file")
public class WSFileService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private LotteryForecastService lotteryService;

	@Autowired
	private LotterySplitService lotterySplitService;

	/**
	 * 保存252组合
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/saveCombination")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> saveCombination(
			@Context HttpServletRequest request) {
		LogUtil.info("start saveCombination!");
		Map<String, Object> map = new HashMap<String, Object>();
		List<LotteryCombination> list = new AlgorithmUtil()
				.combinationSelect(Arrays.asList("0", "1", "2", "3", "4", "5",
						"6", "7", "8", "9"), 5);
		myBatisDAO.delete("LotteryCombinationMapper.deleteCombination");
		myBatisDAO.batchHightInsert(
				"LotteryCombinationMapper.insertBatchCombination", list);
		map.put("status", true);
		LogUtil.info("end saveCombination!");
		return map;
	}

	/**
	 * 添加开奖数据
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/saveRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public int saveRecord(@FormParam("number") String number,
			@FormParam("code") String code) {
		LogUtil.info("insert", "开始添加数据!number=" + number + " code=" + code);
		long sortid = DigitUtil.convertNumber(number);
		LotteryModel model = new LotteryModel(number, code, sortid);
		boolean flag = myBatisDAO.insert("LotteryRecordMapper.insertRecord",
				model);
		LogUtil.info("insert", "添加数据完成!");
		if (flag) {
			return model.getId();
		}
		return -1;
	}

	/**
	 * 更新记录
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/updateRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public int updateRecord(@FormParam("id") String id,
			@FormParam("number") String number, @FormParam("code") String code) {
		LogUtil.info("update", "开始更新记录!number=" + number + " code=" + code
				+ " id=" + id);
		long sortid = DigitUtil.convertNumber(number);
		boolean flag = myBatisDAO.update("LotteryRecordMapper.updateRecord",
				new LotteryModel(Integer.valueOf(id), number, code, sortid));
		LogUtil.info("update", "更新记录完成!number=" + number + " code=" + code
				+ " id=" + id);
		return flag ? Integer.valueOf(id) : -1;
	}

	/**
	 * 导入数据
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/upload")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> upload(@Multipart("file") Attachment file) {
		LogUtil.info("开始导入数据!");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(file.getDataHandler()
					.getInputStream(), LotteryConst.DEFAULT_CHARTSET_NAME));
			String line;
			String[] array;
			List<LotteryModel> list = new ArrayList<LotteryModel>();
			while ((line = br.readLine()) != null) {
				array = line.split(LotteryConst.SEPARATOR);
				if (array.length < 2) {
					array = line.split(LotteryConst.EMTY_SEPARATOR);
				}
				if (array.length >= 2) {
					long sortid = DigitUtil.convertNumber(array[0]);
					list.add(new LotteryModel(array[0], array[1], sortid));
				}
			}
			Collections.reverse(list);
			myBatisDAO.delete("LotteryRecordMapper.truncateRecord");
			flag = myBatisDAO.batchHightInsert(
					"LotteryRecordMapper.insertBatchRecord", list);
			LogUtil.info("导入数据成功!");
		} catch (IOException e) {
			LogUtil.error("upload IOException faied! fileName :", e);
			flag = false;
		} finally {
			CloseUtil.close(br);
		}
		map.put("status", flag);
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/deleteRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteRecord(@FormParam("id") String id) {
		LogUtil.info("delete", "开始删除记录!id=" + id);
		myBatisDAO.delete("LotteryRecordMapper.deleteRecord", id);
		LogUtil.info("delete", "删除记录完成!id=" + id);
		return true;
	}

	/**
	 * 查询252组合
	 * 
	 * @param request
	 * @return
	 */
	@GET
	@Path("/searchCombination")
	@Produces(MediaType.APPLICATION_JSON)
	public PageModel<LotteryCombination> searchCombination(
			@Context HttpServletRequest request) {
		LogUtil.info("start searchCombination!");
		Long count = myBatisDAO
				.select("LotteryCombinationMapper.queryCombinationCount");
		List<LotteryCombination> list = new ArrayList<LotteryCombination>();
		if (count > 0) {
			String pageStr = request.getParameter("page");
			String rowsStr = request.getParameter("rows");
			int rows = StringUtils.isNotEmpty(rowsStr) ? Integer
					.parseInt(rowsStr) : 10;
			// 偏移量
			int offset = StringUtils.isNotEmpty(pageStr) ? (Integer
					.parseInt(pageStr) - 1) * rows : 0;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("offset", offset);
			param.put("rows", rows);
			list = myBatisDAO.selectList(
					"LotteryCombinationMapper.queryCombination", param);
		} else {
			list = new ArrayList<LotteryCombination>();
		}
		LogUtil.info("end searchCombination!");
		return new PageModel<LotteryCombination>(count, list);
	}

	/**
	 * 查询记录
	 * 
	 * @param request
	 * @return
	 */
	@GET
	@Path("/searchRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public PageModel<LotteryModel> searchRecord(
			@Context HttpServletRequest request) {
		LogUtil.info("start searchRecord!");
		Map<String, Object> param = new HashMap<String, Object>();
		String number = request.getParameter("filter");
		param.put("number", number);
		Long count = myBatisDAO.select("LotteryRecordMapper.queryRecordCount",
				param);
		List<LotteryModel> list = new ArrayList<LotteryModel>();
		if (count > 0) {
			String pageStr = request.getParameter("page");
			String rowsStr = request.getParameter("rows");
			int rows = StringUtils.isNotEmpty(rowsStr) ? Integer
					.parseInt(rowsStr) : 10;
			// 偏移量
			int offset = StringUtils.isNotEmpty(pageStr) ? (Integer
					.parseInt(pageStr) - 1) * rows : 0;

			param.put("offset", offset);
			param.put("rows", rows);
			list = myBatisDAO.selectList("LotteryRecordMapper.queryRecord",
					param);
		} else {
			list = new ArrayList<LotteryModel>();
		}
		LogUtil.info("end searchRecord!");
		return new PageModel<LotteryModel>(count, list);
	}

	/**
	 * 解析
	 * 
	 * @param request
	 * @return
	 */
	@GET
	@Path("/searchForecastCode")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GroupData> searchForecastCode(
			@Context HttpServletRequest request) {
		LogUtil.info("开始解析数据!");
		long startTime = System.currentTimeMillis();
		String startStage = request.getParameter("startStage");
		String endStage = request.getParameter("endStage");
		String forecastStage = request.getParameter("forecastStage");
		List<GroupData> resultList;
		if (StringUtils.isNotEmpty(forecastStage)) {
			resultList = lotteryService.forecastLottery(
					Integer.parseInt(forecastStage), startStage, endStage);
		} else {
			resultList = new ArrayList<GroupData>();
		}
		String format = "解析数据完成!总共耗时：{0}秒";
		LogUtil.info(ResourceUtil.format(format,
				(System.currentTimeMillis() - startTime) * 1d / 1000));
		return resultList;
	}

	/**
	 * 初始化数据库
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> init(@Context HttpServletRequest request) {
		LogUtil.info("开始导入数据!");
		Map<String, Object> map = new HashMap<String, Object>();
		DBHolder.setDataSource("mysql");
		myBatisDAO.excuteSQL(String.format(LotteryConst.CREATE_SCHEMA,
				"lottery"));
		DBHolder.setDataSource("common");
		String path = this.getClass().getResource("/").getPath()
				+ File.separator + "database" + File.separator
				+ "create_sql.sql";
		myBatisDAO.excuteSQL(SqlParse.loadSql(path));
		map.put("status", true);
		return map;
	}
}
