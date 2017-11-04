package com.lottery.test;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.dsd.lottery.db.MyBatisDAO;
import com.dsd.lottery.missanalyse.task.MissChildTask;
import com.dsd.lottery.model.LotteryModel;
import com.dsd.lottery.model.miss.MissGroupModel;
import com.dsd.lottery.model.miss.MissRelationModel;
import com.dsd.lottery.model.miss.MissResultModel;
import com.dsd.lottery.util.SpringBeanUtil;

public class MissClient {

	public static void main(String[] args) throws InterruptedException {
		SpringBeanUtil.getBeanFactory();
		MyBatisDAO myBatisDAO = SpringBeanUtil.getBean("myBatisDAO", MyBatisDAO.class);
		MissGroupModel group = new MissGroupModel();
		group.setDigit(3);
		group.setId(1);
		group.setGroup("012");
	    List<LotteryModel> lotteryList = myBatisDAO
	                .selectList("LotteryRecordMapper.queryRecordLimit");
		MissChildTask task = new MissChildTask(lotteryList, group, new AtomicLong(), myBatisDAO){

            @Override
            protected void afterHandle(List<MissResultModel> missResultList,
                    List<MissRelationModel> relationLst) {
            }
		    
		};
		Thread t = new Thread(task);
		t.start();
		t.join();
	}

}
