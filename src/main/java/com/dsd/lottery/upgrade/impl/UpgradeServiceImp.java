package com.dsd.lottery.upgrade.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.DBHolder;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.upgrade.IUpgradeService;
import com.dsd.lottery.util.FileUtil;
import com.dsd.lottery.util.SqlParse;

/**
 * 升级数据库
 * 
 * @author daishengda
 *
 */
@Service("upgradeService")
public class UpgradeServiceImp implements IUpgradeService {

    @Autowired
    private MyBatisDAO myBatisDAO;

    /**
     * 数据库文件目录
     */
    private static final String DB_FOLDER = "database";

    /**
     * 数据库升级文件目录
     */
    private static final String DB_UPGRADE_FOLDER = "upgrade";

    /**
     * 创建数据库SQL文件
     */
    private static final String CREATE_SQL_FILE = "create_sql.sql";

    /**
     * 初始化数据库SQL文件
     */
    private static final String INIT_SQL_FILE = "init_sql.sql";

    private String getResourcesPath() {
        return this.getClass().getResource("/").getPath() + File.separator;
    }

    @Override
    public boolean createSchema() {
        DBHolder.setDataSource("mysql");
        boolean isSuccess =
                myBatisDAO.excuteSQL(String.format(LotteryConst.CREATE_SCHEMA,
                        LotteryConst.DB_SCHEMA_NAME));
        return isSuccess;
    }

    @Override
    public boolean initDatabase() {
        DBHolder.setDataSource("common");
        String path = getResourcesPath() + DB_FOLDER + File.separator;
        if (myBatisDAO.excuteSQL(SqlParse.loadSql(path + CREATE_SQL_FILE))) {
            return myBatisDAO.excuteSQL(SqlParse.loadSql(path + INIT_SQL_FILE));
        }
        return false;
    }

    @Override
    public boolean upgradeDatabase(String fileName) {
        List<File> listFile =
                FileUtil.findFile(new ArrayList<File>(), getResourcesPath() + DB_UPGRADE_FOLDER);
        boolean flag = true;
        Collections.sort(listFile, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {
                File parent1 = o1.getParentFile();
                File parent2 = o2.getParentFile();
                return getLevel(parent1.getName()) - getLevel(parent2.getName());
            }
        });
        for (File file : listFile) {
            if (fileName.equals(file.getName())) {
                if (!myBatisDAO.excuteSQL(SqlParse.loadSql(file))) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    private Integer getLevel(String type) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("dml", 1);
        map.put("ddl", 2);
        return map.get(type);
    }
}
