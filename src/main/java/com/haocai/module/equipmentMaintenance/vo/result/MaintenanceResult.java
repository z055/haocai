package com.haocai.module.equipmentMaintenance.vo.result;

import com.haocai.module.equipmentMaintenance.vo.Maintenance;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceResult {
    int count;
    List<Maintenance> resultTM;

    public MaintenanceResult(int count, ArrayList<Maintenance> resultTM) {
        this.count = count;
        this.resultTM = resultTM;
    }

    public MaintenanceResult() {
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

    public List<Maintenance> getResultTM() {
        return resultTM;
    }

    public void setResultTM(List<Maintenance> resultTM) {
        this.resultTM = resultTM;
    }
}
