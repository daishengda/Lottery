package com.dsd.lottery.missanalyse.constain.impl;

import java.util.HashSet;
import java.util.Set;
import com.dsd.lottery.missanalyse.constain.IMissComparator;

/**
 * 2星组3比较器(必须包含组合中的2个数，并且还要多出一个，包含三个，如23，233、223都算，23456不算)
 * 
 * @author daishengda
 *
 */
public class TwentythreeComparator implements IMissComparator {

    @Override
    public boolean equals(int type, String group, String lotteryNumber) {
        Set<Integer> indexSet = new HashSet<Integer>();
        int groupSize = group.length();
        boolean flag = true;
        for (int i = 0; i < lotteryNumber.length(); i++) {
            int indexOf = group.indexOf(lotteryNumber.charAt(i));
            if (indexOf >= 0) {
                // 说明有重复的
                flag = indexSet.add(indexOf);
            }
            // 重复，并且包含组合中的两个数
            if (indexSet.size() == groupSize && !flag) {
                return true;
            }
        }
        return false;
    }

}
