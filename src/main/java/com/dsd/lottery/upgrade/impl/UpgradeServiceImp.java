package com.dsd.lottery.upgrade.impl;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.DBHolder;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.upgrade.IUpgradeService;
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

  @Override
  public boolean createSchema() {
    DBHolder.setDataSource("mysql");
    boolean isSuccess = myBatisDAO.excuteSQL(String.format(LotteryConst.CREATE_SCHEMA, "lottery"));
    return isSuccess;
  }

  @Override
  public boolean initDatabase() {
    DBHolder.setDataSource("common");
    String path =
        this.getClass().getResource("/").getPath() + File.separator + "database" + File.separator;
    if (myBatisDAO.excuteSQL(SqlParse.loadSql(path + "create_sql.sql"))) {
      return myBatisDAO.excuteSQL(SqlParse.loadSql(path + "init_sql.sql"));
    }
    return false;
  }

}
