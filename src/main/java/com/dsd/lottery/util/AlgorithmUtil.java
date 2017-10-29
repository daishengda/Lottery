package com.dsd.lottery.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.dsd.lottery.model.LotteryCombination;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 算法工具类
 * 
 * @author daishengda
 *
 */
public class AlgorithmUtil {

  private List<LotteryCombination> list;

  public AlgorithmUtil() {
    this.list = new ArrayList<LotteryCombination>();
  }

  /**
   * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
   * 
   * @param n
   * @return
   */
  private long factorial(int n) {
    return (n > 1) ? n * factorial(n - 1) : 1;
  }

  /**
   * 计算排列数，即A(n, m) = n!/(n-m)!
   * 
   * @param n
   * @param m
   * @return
   */
  public long arrangement(int n, int m) {
    return (n >= m) ? factorial(n) / factorial(n - m) : 0;
  }

  /**
   * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
   * 
   * @param n
   * @param m
   * @return
   */
  public long combination(int n, int m) {
    return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
  }

  /**
   * 组合选择（从列表中选择n个组合）
   * 
   * @param dataList 待选列表
   * @param n 选择个数
   * @return
   */
  public List<LotteryCombination> combinationSelect(List<String> dataList, int n) {
    LogUtil.info(String.format("C(%d, %d) = %d", dataList.size(), n,
        combination(dataList.size(), n)));
    combinationSelect(dataList, 0, new String[n], 0);
    return list;
  }

  /**
   * 组合选择
   * 
   * @param dataList 待选列表
   * @param dataIndex 待选开始索引
   * @param resultList 前面（resultIndex-1）个的组合结果
   * @param resultIndex 选择索引，从0开始
   */
  private void combinationSelect(List<String> dataList, int dataIndex, String[] resultList,
      int resultIndex) {
    int resultLen = resultList.length;
    int resultCount = resultIndex + 1;
    if (resultCount > resultLen) { // 全部选择完时，输出组合结果
      StringBuilder left = new StringBuilder();
      StringBuilder right = new StringBuilder();
      for (String result : resultList) {
        left.append(result);
      }
      for (String data : dataList) {
        if (left.indexOf(data) < 0) {
          right.append(data);
        }
      }
      list.add(new LotteryCombination(left.toString(), right.toString()));
      return;
    }

    // 递归选择下一个
    for (int i = dataIndex; i < dataList.size() + resultCount - resultLen; i++) {
      resultList[resultIndex] = dataList.get(i);
      combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
    }
  }

  public static void main(String[] args) {
    // new AlgorithmUtil().combinationSelect(Arrays.asList("0", "1", "2",
    // "3", "4", "5","6","7","8","9"),5);
    List<LotteryCombination> result =
        new AlgorithmUtil().combinationSelect(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), 5);
    for (LotteryCombination lottery : result) {
      System.out.println(lottery.toString());
    }

  }
}
