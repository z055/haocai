package com.haocai.module.trainmegKeep.service;

import com.haocai.module.trainmegKeep.vo.TrainMeg;
import com.haocai.module.trainmegKeep.vo.param.TrainMegParam;
import com.haocai.module.trainmegKeep.vo.result.TrainMegResult;

import java.util.List;

public interface TrainMegService {
    /**
     * 添加实训、比赛维护记录
     * @param trainMeg
     * @return
     */
    public Boolean addTrainMeg(TrainMeg trainMeg);

    public Boolean selectDepTrainMegByDep(TrainMeg trainMeg);

    public TrainMegResult selectTrainMeg(TrainMegParam trainMegParam);

    public Boolean deleteTrainMegById(String id);

    public Boolean updateTrainMegById(TrainMeg trainMeg);
}
