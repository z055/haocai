package com.haocai.module.outCon.dao;

import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.outCon.vo.ConTypeNameVo;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.param.ConTypeNameParam;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.system.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface OutDao {
    /**
     * 单个身份(教师/库管)走getData
     * @param outVoParam
     * @return
     */
    List<OutConVo> getData(OutVoParam outVoParam);

    /**
     * 是双重身份(教师和库管)
     * @param outVoParam
     * @return
     */
    List<OutConVo> getData2(OutVoParam outVoParam);

    /**
     * 单个身份(教师/库管)走getData
     * @param outVoParam
     * @return
     */
    int getCount(OutVoParam outVoParam);

    /**
     * 是双重身份(教师和库管)
     * @param outVoParam
     * @return
     */
    int getCount2(OutVoParam outVoParam);
    List<OutConVo> getScrapData(OutVoParam outVoParam);
    int getScrapCount(OutVoParam outVoParam);
    int setConStatus(@Param("id") String id,@Param("review_id") String review_id);
    int addReview(OutVoParam param);

    int del(String[] id);
    //外界出库状态修改
    int setSbStatus(@Param("id") String id,@Param("review_id") String review_id);
    //获取库存数量
    OutConVo getConNumber(OutConVo outConVo);
    //出库
    int outConNumber(OutConVo outConVo);
    //修改出库状态
    int setOutStatus(OutConVo outConVo);

    //提交出库和审核
    int addOutCon(OutConVo conVo);

    int AddOutConReview(OutReviewVo outReviewVo);
    //申请表中相同耗材的总数量
    Integer getReviewSumNumber(OutConVo outConVo);
    Integer getNumber(@Param("id") String id);
    List<ConTypeNameVo> getCourseName(ConTypeNameParam conTypeNameParam);
    int getCourseCount(ConTypeNameParam conTypeNameParam);

    List<ConTypeNameVo> getConTypeName(ConTypeNameParam conTypeNameParam);
    int getConTypeCount(ConTypeNameParam conTypeNameParam);
    //获取耗材信息
    OutConVo getConById(@Param("id") String id);

    //修改库存总价格
    int updatePriceCons(@Param("id") String id,@Param("prices") String prices);
    //查询审核id
    List<OutReviewVo> getReviewIds(List list);
    //删除审核记录
    int delReviewById(@Param("list") List list);
    //查询审核人id
    OutVoParam getId(OutConVo outConVo);
    //查询审核人id
    OutVoParam getTweId(OutConVo outConVo);
    //查询审核人姓名
    User getName(OutVoParam outVoParam);

}
