package com.haocai.module.inCon.dao;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.system.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
@Repository
public interface InConDao {
    List<InConVo> getData(InConVoParam inConVoParam);
    List<InConVo> getCons();
    int getCount(InConVoParam inConVoParam);
    int setConStatus(@Param("id") String id, @Param("review_id") String review_id);
    int addReview(InConVoParam param);
    int del(String[] ids);
    Integer addCon(InConVoParam param);
    Integer excelAddCon(List  list);
    List<InConVo> selectCon(InConVo inConVo);
    Integer addConNumber (InConVo inConVo);
    Integer inCon(InConVo inConVo);
    Integer setInConStatus(InConVo inConVo);
    //查询审核id
    List<OutReviewVo> getReviewIds(List list);
    //删除审核记录
    int delReviewById(@Param("list") List list);
    //查询审核人id
    InConVoParam getId(InConVo inConVo);
    //查询审核人id
    InConVoParam getTweId(InConVo inConVo);
    //查询审核人姓名
    User getName(InConVoParam inConVoParam);
    String checkConTypeName(@Param("conType") String conType,@Param("conTypeName") String conTypeName);
}
