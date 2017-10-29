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
}
