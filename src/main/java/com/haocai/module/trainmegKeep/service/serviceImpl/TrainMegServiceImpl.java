package com.haocai.module.trainmegKeep.service.serviceImpl;

import com.haocai.module.trainmegKeep.dao.TrainMegDao;
import com.haocai.module.trainmegKeep.service.TrainMegService;
import com.haocai.module.trainmegKeep.util.ReString;
import com.haocai.module.trainmegKeep.vo.TrainMeg;
import com.haocai.module.trainmegKeep.vo.param.TrainMegParam;
import com.haocai.module.trainmegKeep.vo.result.TrainMegResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TrainMegServiceImpl implements TrainMegService {

    @Autowired
    TrainMegDao trainMegDao;

    /**
     * 添加实训、比赛维护记录
     * @param trainMeg
     * @return
     */
    @Override
    public Boolean addTrainMeg(TrainMeg trainMeg) {
        String id = UUID.randomUUID().toString();
        trainMeg.setId(id);
        ReString reString = new ReString();
        String depTrainMeg = reString.getDepTrainMeg(trainMeg);
        trainMeg.setDepTrainMeg(depTrainMeg);
        String s = trainMegDao.selectNameByAccount(trainMeg.getAccount());
        trainMeg.setPrincipal(s);
        int i = trainMegDao.insertTrainMeg(trainMeg);
        return i>=1;
    }

    /**
     * 查看添加或修改时是否存在相同depTrainMeg数据
     * @param trainMeg
     * @return 如果是true代表不存在相同的
     */
    @Override
    public Boolean selectDepTrainMegByDep(TrainMeg trainMeg) {
        ReString reString = new ReString();
        String depTrainMeg = reString.getDepTrainMeg(trainMeg);
        List<String> selectDepTrainMeg = trainMegDao.selectDepTrainMegByDep(depTrainMeg);
        return CollectionUtils.isEmpty(selectDepTrainMeg);
    }

    /**
     * 查询实训、大赛维护数据
     * @param trainMegParam
     * @return
     */
    @Override
    public TrainMegResult selectTrainMeg(TrainMegParam trainMegParam) {
        trainMegParam.setPage((trainMegParam.getPage() - 1) * trainMegParam.getLimit());
        List<TrainMeg> trainMegs = trainMegDao.selectTrainMeg(trainMegParam);
        int i = trainMegDao.selectTrainMegCount(trainMegParam);
        TrainMegResult trainMegResult = new TrainMegResult();
        trainMegResult.setResultTM(trainMegs);
        trainMegResult.setCount(i);
        return trainMegResult;
    }

    @Override
    public Boolean deleteTrainMegById(String id) {
        int i = trainMegDao.deleteTrainMegById(id);
        return i>=1;
    }

    @Override
    public Boolean updateTrainMegById(TrainMeg trainMeg) {
        ReString reString = new ReString();
        String depTrainMeg = reString.getDepTrainMeg(trainMeg);
        trainMeg.setDepTrainMeg(depTrainMeg);
        int i = trainMegDao.updateTrainMegById(trainMeg);
        return i>=0;
    }
}
