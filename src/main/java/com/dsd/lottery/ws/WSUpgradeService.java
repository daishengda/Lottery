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
import com.dsd.lottery.upgrade.IUpgradeService;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 遗漏算法服务接口
 * 
 * @author daishengda
 *
 */
@Path("/upgrade")
public class WSUpgradeService {

    @Autowired
    private IUpgradeService upgradeService;
    
    //暂时不执行dml脚本，报错了
    @Autowired
    private IMissAnalyse missAnalyse;

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
        boolean status = false;
        if (upgradeService.createSchema() && upgradeService.initDatabase()) {
            status = true;
        }
        map.put("status", status);
        return map;
    }
    
    /**
     * 按文件名升级数据库
     * @param file
     * @return
     */
    @POST
    @Path("/upgradeDatabase")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> upgradeDatabase() {
        LogUtil.info("开始升级数据库!");
        Map<String, Object> map = new HashMap<String, Object>();
        boolean status = false;
        if (upgradeService.upgradeDatabase("UPGRADE_DDL_20171029.sql")) {
            if(missAnalyse.deleteMissGroup())
            {
                missAnalyse.createMissGroup();
            }
            status = true;
        }
        map.put("status", status);
        LogUtil.info("end upgradeDatabase!");
        return map;
    }
}
