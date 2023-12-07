package com.haocai.module.outCon.service;

import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;

import java.util.List;

public interface OutConAutoService {
   OutVoResult getData(OutVoParam param);
   int audit(OutVoParam param);
   int rejected(OutVoParam param);
   OutConVo getHaoCaiData(String review_id);
   List<InConVoParam> getHaoCaiDatas(List review_id);
   List<InConVoParam> showOption(String review_id);
}
