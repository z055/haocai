package com.haocai.module.sbModule.service;

import com.haocai.module.sbModule.vo.ConOut;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.sbModule.vo.resulet.SbResult;
import org.apache.ibatis.annotations.Param;
import scala.math.BigDecimal;

import java.util.List;
import java.util.Map;

public interface SbService {
    int getConOutNum(Map<String, String> map);
    SbResult getBorrows(SbParam sbVo);
    int addSb(Map<String, String> map);
    int addGiveBackSb(Map<String, String> map);
    int delSb(SbParam sbParam);
    int updateSbReview(Map<String, String> map );
    List<SbVo> out(List<SbVo> sbVos);
    Boolean storage(List<SbVo> sbVos);
}
