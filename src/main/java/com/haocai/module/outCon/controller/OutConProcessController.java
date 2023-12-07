package com.haocai.module.outCon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.outCon.service.OutConProcessService;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/outConProcess")
public class OutConProcessController {
    @Autowired
    private OutConProcessService outConProcessService;

    @ResponseBody
    @RequestMapping("/getData")
    public Object getData(OutVoParam outVoParam){
        outVoParam=(OutVoParam) ConversionStrUtil.ConversionStr(outVoParam);

        System.err.println(outVoParam.getCon_type());
        OutVoResult result= outConProcessService.getData(outVoParam);
        return  new JsonTemplate("查询成功",result.getList(),result.getCount()).toString();
    }
    @ResponseBody
    @RequestMapping("/getDepartment")
    public Object getDepartment() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(outConProcessService.getDept());
    }
    @ResponseBody
    @RequestMapping("/getMajor")
    public Object getMajor(String department) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(outConProcessService.getMajor(department));
    }
}
