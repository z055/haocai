package com.haocai.module.outCon.controller;

import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.outCon.service.OutConAutoService;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/outConAuto")
@ResponseBody
public class OutConAutoController {
    @Autowired
    private OutConAutoService outConAutoService;
    /**
     *
     * @param outVoParam
     * @param session
     * @return
     *   审核流程查询
     */
    @RequestMapping("/getData")
    public Object getData(OutVoParam outVoParam, HttpSession session){
       outVoParam=(OutVoParam) ConversionStrUtil.ConversionStr(outVoParam);
        User user=(User)session.getAttribute("user");
        String roleId=user.getUserId().toString();
        outVoParam.setRoleId(roleId);
        OutVoResult result= outConAutoService.getData(outVoParam);
        int count=result.getCount();
        return new JsonTemplate("查询成功",result.getList(),count).toString();
    }
    @RequestMapping("/getHaoCaiData")
    public Object getHaoCaiData(String review_id){
        List<OutConVo> list=new ArrayList<>();
        list.add(outConAutoService.getHaoCaiData(review_id));
       return new JsonTemplate("成功",list,1).toString();
    }
    @RequestMapping("/getHaoCaiDatas")
    public Object getHaoCaiDatas(OutVoParam outVoParam){
       return new JsonTemplate("1",outConAutoService.getHaoCaiDatas(outVoParam.getReviewId()),1).toString();
    }

    @RequestMapping("/audit")
    public Object audit(@RequestBody OutVoParam param){
      int s=  outConAutoService.audit(param);
      if (s==-1){
          return new JsonResult("-1",null,"库存不足审核无法通过");
      }
            return new JsonResult("1",null,"审核成功");
    }
    @RequestMapping("/rejected")
    public Object rejected(@RequestBody OutVoParam param){
        int s= outConAutoService.rejected(param);
            return new JsonResult("1",null,"审核成功");
    }
    @RequestMapping("/showOption")
    @ResponseBody
    public Object showOption(String review_id){
        List<InConVoParam>  outVoParams=outConAutoService.showOption(review_id);
        return  outVoParams;
    }

}
