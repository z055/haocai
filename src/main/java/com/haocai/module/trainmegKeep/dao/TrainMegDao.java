package com.haocai.module.trainmegKeep.dao;

import com.haocai.module.trainmegKeep.vo.TrainMeg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainMegDao {

    /**
     * 添加实训、比赛维护记录
     * @param trainMeg
     * @return
     */
    public int insertTrainMeg(TrainMeg trainMeg);

    /**
     * 根据账号查姓名
     * @param account
     * @return
     */
    public String selectNameByAccount(@Param("account") String account);

    public List<TrainMeg> selectTrainMeg(TrainMeg trainMeg);
    public int selectTrainMegCount(TrainMeg trainMeg);

    public int deleteTrainMegById(@Param("id") String id);
    public int updateTrainMegById(TrainMeg trainMeg);

    public List<String> selectDepTrainMegByDep(String depTrainMeg);
}
