package com.haocai.module.outCon.service;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.ConTypeNameParam;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.ConTypeNameResult;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OutService {
    OutVoResult getData(OutVoParam outVoParam,List<String> roleNameList);

    int addReview(Map<String ,String > map);
    ConTypeNameResult getConTypeName(ConTypeNameParam conTypeNameParam);
    int  addReviewTow(Map<String ,String > map);

    /**
     * 通过耗材id查询该类耗材在审核表中的总数量
     * @param id
     * @return
     */
    Integer getReviewSumNumber(String id);
    Integer getNumber(String id);
    int del(String[] ids);

    List<OutConVo> outCons(List<OutConVo> outConVos);

    OutVoResult getScrapData(OutVoParam outVoParam);
    int addCheck(Map<String, String> map);

}
