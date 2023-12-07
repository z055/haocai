package com.haocai.module.inCon.dao;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.InReviewVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InConAuditDao {
    //页面渲染数据
    List<InConVo> getData(InConVoParam param);
    int getCount(InConVoParam param);
    //获取审核耗材的具体数据
    InConVo getHaoCaiData(@Param("review_id") String review_id);
    //根据id 跟新自己的审核记录数据
    int setStatusById(@Param("id") String id,@Param("status") String status,@Param("audit_body") String audit_body);
    //根据id获取到审核记录的数据
    InConVoParam getDataById(@Param("id") String id);
    //根据审核人 和 审核标号 修改上下级 审核人的状态
    int setStatusByReId(@Param("status") String status,@Param("re_pe_id") String re_pe_id,@Param("review_id") String review_id);
    //修改 入出库表里面的审核状态
    int setStatus(@Param("status") String status,@Param("review_id") String review_id);
    //修改 入库出库表里面的 最终显示状态
    int setShowStatus(@Param("status_z") String status_z,@Param("review_id") String review_id);
    //修改库存
    int addConNumber(@Param("id") String id,@Param("number") String number);
    //添加耗材
    int addCon(InConVo inConVo);
    //修改共享状态
    int updateState();
    //查询库存
    String selectCon(InConVo inConVo);

    //批量审核sql
    //根据审核id获取多个耗材信息
    List<InConVoParam> getHaoCaiDatas(@Param("review_id") List review_id);
}
