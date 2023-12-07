package com.haocai.module.trainmegKeep.util;

import com.haocai.module.trainmegKeep.vo.TrainMeg;

public class ReString {
    public String getDepTrainMeg(TrainMeg trainMeg) {
        String depTrainMeg = trainMeg.getDepartment() + ":" + trainMeg.getName();
        return depTrainMeg;
    }
}
