package com.dsd.lottery.missanalyse.constain;

/**
 * 遗失比较
 * 
 * @author daishengda
 *
 */
public interface IMissComparator {

    boolean equals(int type, String group, String lotteryNumber);
}
