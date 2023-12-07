package com.haocai.module.inCon.controller;
import com.haocai.module.inCon.service.InConAuditService;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
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
import java.util.HashMap;
import java.util.List;
@Controller
@ResponseBody
@RequestMapping("/inConAudit")
public class InConAuditController {
    @Autowired
    private InConAuditService inConAuditService;
    @RequestMapping("/getData")
    public Object getData(InConVoParam inConVoParam, HttpSession session){
       inConVoParam=(InConVoParam) ConversionStrUtil.ConversionStr(inConVoParam);
        User user=(User) session.getAttribute("user");
       String roleId=user.getUserId().toString();
       inConVoParam.setRoleId(roleId);
      InConVoResult result= inConAuditService.getData(inConVoParam);
        int count=result.getCount();
        return new JsonTemplate("成功",result.getList(),count).toString();
    }

    @RequestMapping("/getHaoCaiData")
    public Object getHaoCaiData(String review_id){
        List<InConVo> list=new ArrayList();
        list.add(inConAuditService.getHaoCaiData(review_id));
        return new JsonTemplate("1",list,1).toString();
    }
    @RequestMapping("/getHaoCaiDatas")
    public Object getHaoCaiDatas(InConVoParam inConVoParam){
        return new JsonTemplate("1",inConAuditService.getHaoCaiDatas(inConVoParam.getReviewId()),1).toString();
    }
    @RequestMapping("/audit")
    public Object audit(@RequestBody InConVoParam param){
      int s=  inConAuditService.audit(param);
        return new JsonResult("1",null,"审核成功");
    }

    @RequestMapping("/rejected")
    public Object rejected(@RequestBody InConVoParam param){
        int s= inConAuditService.rejected(param);
        return new JsonResult("1",null,"审核成功");
    }
}
