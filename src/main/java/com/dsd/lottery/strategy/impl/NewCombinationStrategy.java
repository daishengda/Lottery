package com.dsd.lottery.strategy.impl;

import java.util.List;
import com.dsd.lottery.forecast.model.QueryResult;
import com.dsd.lottery.missanalyse.constain.IMissComparator;
import com.dsd.lottery.missanalyse.constain.impl.MissComparatorFactory;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.StorageModel;
import com.dsd.lottery.strategy.IStrategy;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 252种组合算法
 * 
 * @author daishengda
 *
 */
public class NewCombinationStrategy implements IStrategy {

    /**
     * 用3星组6的比较器
     */
    private IMissComparator comparator;

    /**
     * 中三个便满足
     */
    private static final int THREE = 3;

    @Override
    public void operate(List<LotteryModel> lotteryList, StorageModel<QueryResult> storage,
            char[][] charArrays, String left, String right, int stage) {
        String[] currentArrays = convertChar(charArrays);
        int size = lotteryList.size();
        try {
            for (int i = 0; i < size; i++) {
                if (i + stage < size) {
                    splitContrast(lotteryList, storage, i, i + stage, currentArrays, left, right);
                }
            }
        } catch (InterruptedException e) {
            LogUtil.error("解析数据失败！", e);
        }
    }

    public NewCombinationStrategy() {
        comparator = MissComparatorFactory.getMissComparator(MissComparatorFactory.DEFAULT_KEY);
    }

    private String[] convertChar(char[][] charArrays) {
        String[] codeArrays = new String[charArrays.length];
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char[] cs : charArrays) {
            sb.setLength(0);
            for (char c : cs) {
                sb.append(c);
            }
            codeArrays[index++] = sb.toString();
        }
        return codeArrays;
    }

    private void splitContrast(List<LotteryModel> lotteryList, StorageModel<QueryResult> storage,
            int index, int stage, String[] currentArrays, String left, String right)
            throws InterruptedException {
        String code;
        // 正向
        boolean positive = true;
        // 反向
        boolean reverse = true;
        int j = 0;

        for (int i = index; i < stage; i++) {
            code = lotteryList.get(i).getCode();
            boolean currentFlag = comparator.equals(THREE, left, currentArrays[j++]);
            boolean codeFlag = comparator.equals(THREE, left, code);
            if (currentFlag == codeFlag) {
                positive = false;
            } else {
                reverse = false;
            }

            // 如果两个都是false，说明不满足，直接返回
            if (!positive && !reverse) {
                return;
            }
        }
        storage.push(new QueryResult(lotteryList.get(index).getNumber(), left, right, 0, stage
                - index));
    }
}
