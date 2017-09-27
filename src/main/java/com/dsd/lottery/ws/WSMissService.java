package com.dsd.lottery.ws;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsd.lottery.missanalyse.IMissAnalyse;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 遗漏算法服务接口
 * 
 * @author daishengda
 *
 */
@Path("/miss")
public class WSMissService {

	@Autowired
	private IMissAnalyse missAnalyse;

	/**
	 * 保存遗漏组合(包括2、3、4、5位所有组合)
	 * 
	 * @param request
	 * @return
	 */
	@POST
	@Path("/saveMissGroup")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> saveMissGroup(@Context HttpServletRequest request) {
		LogUtil.info("start saveMissGroup!");
		Map<String, Object> map = new HashMap<String, Object>();
		if (missAnalyse.deleteMissGroup()) {
			missAnalyse.createMissGroup();
			map.put("status", true);
		} else {
			map.put("status", false);
			LogUtil.error("delete MissGroup faild!");
		}
		LogUtil.info("end saveMissGroup!");
		return map;
	}
}
