package com.haocai.module.outCon.service.serviceImpl;

import com.haocai.module.outCon.dao.OutConProcessDao;
import com.haocai.module.outCon.service.OutConProcessService;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class OutConProcessServiceImpl implements OutConProcessService {
    @Autowired
    private OutConProcessDao outConProcessDao;
    @Override
    public OutVoResult getData(OutVoParam outVoParam) {
        outVoParam.setPage((outVoParam.getPage() - 1) * outVoParam.getLimit());
        List<OutConVo> list = outConProcessDao.getData(outVoParam);
        int count = outConProcessDao.getCount(outVoParam);
        OutVoResult outVoResult = new OutVoResult();
        outVoResult.setList(list);
        outVoResult.setCount(count);
        return outVoResult;
    }

    @Override
    public List<String> getDept() {
        return outConProcessDao.getDept();
    }

    @Override
    public List<String> getMajor(String department) {
        return outConProcessDao.getMajor(department);
    }
}
