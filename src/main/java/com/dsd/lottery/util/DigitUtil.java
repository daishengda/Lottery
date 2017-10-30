package com.dsd.lottery.util;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.math.NumberUtils;

public class DigitUtil {

    /**
     * 位数转换描述
     * 
     * @param num
     * @return
     */
    public static String convertUnit(Integer num) {
        return convertUnit(num, "位");
    }

    /**
     * 位数转换描述
     * 
     * @param num
     * @return
     */
    public static String convertUnit(Integer num, String desc) {
        String info;
        switch (num) {
            case 0:
                info = "万";
                break;
            case 1:
                info = "千";
                break;
            case 2:
                info = "百";
                break;
            case 3:
                info = "十";
                break;
            case 4:
                info = "个";
                break;
            default:
                info = "未知";
                break;
        }
        info += desc;
        return info;
    }

    /**
     * 根据字符串的数字部分进行合并，转换为long，方便排序
     * 
     * @param number
     * @return
     */
    public static long convertNumber(String str) {
        StringBuilder sortid = new StringBuilder();
        String temp;
        for (int i = 0; i < str.length(); i++) {
            temp = str.substring(i, i + 1);
            if (NumberUtils.isNumber(temp)) {
                sortid.append(temp);
            }
        }
        return Long.parseLong(sortid.toString());
    }

    /**
     * 判断开奖号码是否包含在组合中
     * 
     * @param type 类型
     * @param group 组合
     * @param lotteryNumber 彩票号码
     * @return
     */
    public static boolean isContain(int type, String group, String lotteryNumber) {
        if (type > group.length()) {
            return isContainWhole(group, lotteryNumber);
        }
        for (int i = 0; i < lotteryNumber.length(); i++) {
            if (group.contains(String.valueOf(lotteryNumber.charAt(i)))) {
                if (--type == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断开奖号码是否包含在组合中（完成匹配的）
     * 
     * @param group 组合
     * @param lotteryNumber 彩票号码
     * @return
     */
    public static boolean isContainWhole(String group, String lotteryNumber) {
        Set<Integer> indexSet = new HashSet<Integer>();
        int groupSize = group.length();
        for (int i = 0; i < lotteryNumber.length(); i++) {
            indexSet.add(group.indexOf(lotteryNumber.charAt(i)));
            if (indexSet.size() == groupSize) {
                return true;
            }
        }
        return false;
    }
}
