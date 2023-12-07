package com.haocai.module.trainmegKeep.vo.result;

import com.haocai.module.trainmegKeep.vo.TrainMeg;

import java.util.ArrayList;
import java.util.List;

public class TrainMegResult {
    int count;
    List<TrainMeg> resultTM;

    public TrainMegResult(int count, ArrayList<TrainMeg> resultTM) {
        this.count = count;
        this.resultTM = resultTM;
    }

    public TrainMegResult() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TrainMegResult{" +
                "count=" + count +
                ", resultTM=" + resultTM +
                '}';
    }

    public List<TrainMeg> getResultTM() {
        return resultTM;
    }

    public void setResultTM(List<TrainMeg> resultTM) {
        this.resultTM = resultTM;
    }
}
