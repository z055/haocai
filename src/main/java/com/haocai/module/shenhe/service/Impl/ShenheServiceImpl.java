package com.haocai.module.shenhe.service.Impl;

import com.haocai.module.shenhe.dao.ShenheDao;
import com.haocai.module.shenhe.entity.ReviewTable;
import com.haocai.module.shenhe.service.ShenheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShenheServiceImpl implements ShenheService {


    @Autowired
    private ShenheDao shenheDao;

    @Override
    public int insertShenhe(String [] reviewId,String [] projectName, String [] user,String [] role) {
        int index = 0;
        for (int j = 0; j < reviewId.length; j++) {
            for (int i = 0; i < 3; i++) {
                ReviewTable reviewTable = new ReviewTable();
                reviewTable.setId(UUID.randomUUID().toString().replace("-", ""));
                reviewTable.setReviewId(reviewId[j]);
                reviewTable.setProjectName(projectName[j]);
                reviewTable.setRePeId(user[i]);
                reviewTable.setRole(role[i]);
                if (i > 0) {
                    reviewTable.setBeforeRe(user[i-1]);
                    System.out.println("useri-1="+user[i-1]);
                }
                if (i < 2) {
                    reviewTable.setAfterRe(user[i+1]);
                }
                if(i == 0) {
                    reviewTable.setStatus("0");
                }
                index += shenheDao.insertShenhe(reviewTable);
            }
        }
        return index;
    }
}
