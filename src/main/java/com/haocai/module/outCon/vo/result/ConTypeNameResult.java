package com.haocai.module.outCon.vo.result;

import com.haocai.module.outCon.vo.ConTypeNameVo;
import com.haocai.module.outCon.vo.OutConVo;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
public class ConTypeNameResult {
    private int count;
    private List<ConTypeNameVo> list;

    public void setList(List<ConTypeNameVo> list) {
        this.list = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List<ConTypeNameVo> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "OutVoResult{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }
}
