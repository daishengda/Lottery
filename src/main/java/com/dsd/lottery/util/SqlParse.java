package com.dsd.lottery.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 解析sql文件工具类
 * 
 * @author acer-pc
 *
 */
public class SqlParse {

    public static String loadSql(File sqlFile) {
        StringBuilder sql = new StringBuilder();
        InputStream sqlFileIn = null;
        try {
            sqlFileIn = new FileInputStream(sqlFile);
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sql.append(new String(buff, 0, byteRead, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(sqlFileIn);
        }
        return sql.toString();
    }

    public static String loadSql(String sqlFile) {
        return loadSql(new File(sqlFile));
    }
}
