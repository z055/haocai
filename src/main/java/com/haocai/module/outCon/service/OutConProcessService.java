package com.haocai.module.outCon.service;

import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.Dept;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
public interface OutConProcessService {
    OutVoResult getData(OutVoParam outVoParam);
    List<String> getDept();
    List<String> getMajor(String department);
}
