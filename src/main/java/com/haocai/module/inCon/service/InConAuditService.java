package com.haocai.module.inCon.service;

import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InConAuditService {
    InConVoResult getData(InConVoParam param);
    int audit(InConVoParam param);
    int rejected(InConVoParam param);
    InConVo getHaoCaiData(String review_id);
    List<InConVoParam> getHaoCaiDatas( List review_id);
}
