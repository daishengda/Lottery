package com.dsd.lottery.missanalyse.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dsd.lottery.constant.LotteryConst;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.enums.EnumMissGroup;
import com.dsd.lottery.missanalyse.IMissAnalyse;
import com.dsd.lottery.missanalyse.task.MissMasterTask;
import com.dsd.lottery.model.LotteryCombination;
import com.dsd.lottery.model.miss.MissGroupModel;
import com.dsd.lottery.model.miss.MissInfoModel;
import com.dsd.lottery.model.miss.MissInfoModel.MissModel;
import com.dsd.lottery.model.miss.MissRelationModel;
import com.dsd.lottery.model.miss.MissResultModel;
import com.dsd.lottery.util.AlgorithmUtil;
import com.dsd.lottery.util.ResourceUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 遗漏解析算法实现类
 * 
 * @author daishengda
 *
 */
@Service("missAnalyse")
public class MissAnalyseImpl implements IMissAnalyse {

    @Autowired
    private MyBatisDAO myBatisDAO;

    @Override
    public void createMissGroup() {
        List<String> dataList = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        EnumMissGroup[] enumArray = EnumMissGroup.values();
        List<MissGroupModel> groupList = new ArrayList<MissGroupModel>();
        List<LotteryCombination> cmbList;
        MissGroupModel group;
        for (EnumMissGroup enumMiss : enumArray) {
            cmbList = new AlgorithmUtil().combinationSelect(dataList, enumMiss.getDigit());
            for (LotteryCombination cmb : cmbList) {
                group = new MissGroupModel(enumMiss.getDigit(), cmb.getLeft());
                groupList.add(group);
            }
        }
        myBatisDAO.insert("LotteryMissMapper.insertBatchMissGroup", groupList);
    }

    @Override
    public boolean deleteMissGroup() {
        int code = myBatisDAO.delete("LotteryMissMapper.deleteMissGroup");
        return MyBatisDAO.ERROR_CODE != code;
    }

    @Override
    public void saveMissResult() {
        MissMasterTask task = new MissMasterTask(myBatisDAO);
        Thread thread = new Thread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LogUtil.error("saveMissResult MissGroup faild!", e);
        }
    }

    @Override
    public List<MissInfoModel> queryMissInfo(Map<String, String> param) {
        List<MissGroupModel> missGroupList =
                myBatisDAO.selectList("LotteryMissMapper.queryMissGroup", param);
        List<Integer> groupIds = new ArrayList<Integer>();
        for (MissGroupModel missGroup : missGroupList) {
            groupIds.add(missGroup.getId());
        }
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("groupIds", groupIds);
        List<MissRelationModel> missRelationList =
                myBatisDAO.selectList("LotteryMissMapper.queryMissRelation", paramObj);
        List<MissInfoModel> missInfoList = new ArrayList<MissInfoModel>();
        if (missRelationList.isEmpty()) {
            return missInfoList;
        }
        List<Long> resultIds = new ArrayList<Long>();
        Map<Integer, List<Long>> groupIdMap = new HashMap<Integer, List<Long>>();
        for (MissRelationModel missRelation : missRelationList) {
            buildMap(groupIdMap, missRelation.getGroupId(), missRelation.getResultId());
            resultIds.add(missRelation.getResultId());
        }
        paramObj.clear();
        paramObj.put("ids", resultIds);
        List<MissResultModel> missResultList =
                myBatisDAO.selectList("LotteryMissMapper.queryMissResult", paramObj);
        Map<Long, MissResultModel> missResultMap = new HashMap<Long, MissResultModel>();
        for (MissResultModel missResult : missResultList) {
            missResultMap.put(missResult.getId(), missResult);
        }
        MissInfoModel missInfo;
        for (MissGroupModel missGroup : missGroupList) {
            int digit = missGroup.getDigit();
            missInfo = new MissInfoModel();
            missInfo.setGroup(missGroup.getGroup());
            missInfo.setDigit(digit);
            List<Long> ids = groupIdMap.get(missGroup.getId());
            for (Long resultId : ids) {
                MissResultModel result = missResultMap.get(resultId);
                int type = result.getType();
                String desc;
                if (type > digit) {
                    desc =
                            Long.compare(result.getEnd(), 0L) == 0 ? ResourceUtil.format(
                                    LotteryConst.CURRENT_MISS_COLUMN_NAME_WHOLE, digit, type)
                                    : ResourceUtil.format(LotteryConst.MAX_MISS_COLUMN_NAME_WHOLE,
                                            digit, type);
                } else {
                    desc =
                            Long.compare(result.getEnd(), 0L) == 0 ? ResourceUtil.format(
                                    LotteryConst.CURRENT_MISS_COLUMN_NAME, type) : ResourceUtil
                                    .format(LotteryConst.MAX_MISS_COLUMN_NAME, type);
                }

                missInfo.getMissList().add(
                        new MissModel(result.getBegin(), result.getEnd(), result.getType(), desc,
                                result.getMissPeriods()));
            }
            // 排序
            Collections.sort(missInfo.getMissList(), new Comparator<MissModel>() {
                @Override
                public int compare(MissModel o1, MissModel o2) {
                    int diffType = o1.getType() - o2.getType();
                    return diffType == 0 ? (int) (o1.getEnd() - o2.getEnd()) : diffType;
                }
            });
            missInfoList.add(missInfo);
        }
        return missInfoList;
    }

    private <K, T> void buildMap(Map<K, List<T>> map, K key, T value) {
        List<T> lst = map.get(key);
        if (lst == null) {
            lst = new ArrayList<T>();
            map.put(key, lst);
        }
        lst.add(value);
    }

}
