package com.dsd.lottery.missanalyse.constain.impl;

import com.dsd.lottery.missanalyse.constain.IMissComparator;


/**
 * 最普通的一种算法(除2星组3和3星组6不一样,按类型来计算，类型是包含的组合次数(重复也算))
 * 
 * @author daishengda
 *
 */
public class GeneralMissComparator implements IMissComparator {

    @Override
    public boolean equals(int type, String group, String lotteryNumber) {
        for (int i = 0; i < lotteryNumber.length(); i++) {
            if (group.contains(String.valueOf(lotteryNumber.charAt(i)))) {
                if (--type == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
