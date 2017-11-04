package com.dsd.lottery.missanalyse.constain.impl;

import java.util.HashSet;
import java.util.Set;
import com.dsd.lottery.missanalyse.constain.IMissComparator;

/**
 * 3星组6比较器(必须包含组合中的3个数，如123，1233、1223、12233都算)
 * 
 * @author daishengda
 *
 */
public class ThirtysixComparator implements IMissComparator {

    @Override
    public boolean equals(int type, String group, String lotteryNumber) {
        Set<Integer> indexSet = new HashSet<Integer>();
        int groupSize = group.length();
        for (int i = 0; i < lotteryNumber.length(); i++) {
            int indexOf = group.indexOf(lotteryNumber.charAt(i));
            if (indexOf >= 0) {
                indexSet.add(indexOf);
            }
            if (indexSet.size() == groupSize) {
                return true;
            }
        }
        return false;
    }

}
