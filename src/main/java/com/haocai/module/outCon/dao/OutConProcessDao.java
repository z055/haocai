package com.haocai.module.outCon.dao;

import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.system.vo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface OutConProcessDao {
    List<OutConVo> getData(OutVoParam outVoParam);
    int getCount(OutVoParam outVoParam);
    List<String> getDept ();
    List<String> getMajor (@Param("department") String department);
}
