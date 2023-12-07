package com.haocai.module.inCon.service;

import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.User;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InConService {
    InConVoResult getData(InConVoParam inConVoParam);

    int addReview(Map<String ,String > map);

    int del(String[] ids);

    int addCon(InConVoParam param);

    List<InConVo> getCons();
    Object InExcel(String fileUrl, User user);
   
    Integer inCon(List<InConVo> cons);
}
