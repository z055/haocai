package com.haocai.module.outCon.controller;

import com.haocai.module.outCon.service.OutService;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.ConTypeNameVo;
import com.haocai.module.outCon.vo.param.ConTypeNameParam;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.ConTypeNameResult;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/outCon")
public class OutController {
    @Autowired
    private OutService outService;
    @ResponseBody
    @RequestMapping("/getData")
    public Object getData(OutVoParam outVoParam, HttpSession session){
        System.err.println(outVoParam);
       outVoParam=(OutVoParam) ConversionStrUtil.ConversionStr(outVoParam);
        User user= (User) session.getAttribute("user");
        String[] roleName=user.getRoleName().split(",");

        List<String> roleNameList = Arrays.asList(roleName);
        if (roleNameList.contains("库管员")){
           outVoParam.setLibraryName(user.getUserId().toString());
        }else {
            outVoParam.setApplicant(user.getAccount());
        }
        System.err.println(outVoParam.getCon_type());
       OutVoResult result= outService.getData(outVoParam,roleNameList);
        return  new JsonTemplate("查询成功",result.getList(),result.getCount()).toString();
    }
    @ResponseBody
    @RequestMapping("/getScrapData")
    public Object getScrapData(OutVoParam outVoParam, HttpSession session){
        System.err.println(outVoParam);
        outVoParam=(OutVoParam) ConversionStrUtil.ConversionStr(outVoParam);
        User user= (User) session.getAttribute("user");
        outVoParam.setApplicant(user.getAccount());
        outVoParam.setLibraryName(user.getUserId().toString());
        OutVoResult result= outService.getScrapData(outVoParam);
        return  new JsonTemplate("查询成功",result.getList(),result.getCount()).toString();
    }
    @ResponseBody
    @RequestMapping("/addReview")
    public Object addReview(@RequestBody HashMap<String,String> map){

        outService.addReview(map);
        return "0";
    }

    @ResponseBody
    @RequestMapping("/getConTypeName")
    public Object getConTypeName(ConTypeNameParam conTypeNameParam){
        conTypeNameParam =  (ConTypeNameParam)ConversionStrUtil.ConversionStr(conTypeNameParam);
        ConTypeNameResult result = outService.getConTypeName(conTypeNameParam);
        return  new JsonTemplate("查询成功",result.getList(),result.getCount()).toString();
    }
    /**
     * 提交出库审核
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/addReviewTow")
    public Object addReviewTow(@RequestBody HashMap<String,String> map,HttpSession session){
        System.err.println(map.toString());
        User user= (User) session.getAttribute("user");

        int outNumber=Integer.parseInt(map.get("outNumber"));
        String id = map.get("id");
        int number=outService.getNumber(id);
        Integer reviewSumNumber = outService.getReviewSumNumber(id);

        if(reviewSumNumber == null){
            reviewSumNumber = 0;
        }
        System.out.println(reviewSumNumber);
        if (outNumber>number-reviewSumNumber){
            return new JsonResult(null,null,"当前可申请的数量为"+(number-reviewSumNumber)+",申请出库的数量大于可以出库的数量");
        }
        map.put("applicant",user.getAccount());
        map.put("applicantName",user.getName());
        outService.addReviewTow(map);
        return new JsonResult(null,null,"success");
    }
    @ResponseBody
    @RequestMapping("conDel")
    public Object conDel(String id){
        String[] ids = id.split(",");
        int i= outService.del(ids);
        if (i==ids.length){
            return  new JsonResult("1",null,"删除成功");
        }
        return new JsonResult("0",null,"部分删除失败");
    }
    @ResponseBody
    @RequestMapping("/outCons")
    public Object outCons(@RequestBody List<OutConVo> outConVos){
            List<OutConVo> outErrCons=outService.outCons(outConVos);
            if (outErrCons.isEmpty()){
                return new JsonResult("1",null,"出库成功");
            }
            return new JsonResult("0",outErrCons,"部分耗材库存不足部分出库失败");
    }
    @ResponseBody
    @RequestMapping("/addCheck")
    public Object addCheck(@RequestBody HashMap<String,String> map,HttpSession session){
        System.err.println(map.toString());
        User user= (User) session.getAttribute("user");
        int number=Integer.parseInt(map.get("number"));
        int outNumber=Integer.parseInt(map.get("outNumber"));
        if (outNumber>number){
            return new JsonResult(null,null,"库存不足");
        }
        map.put("applicant",user.getAccount());
        map.put("applicantName",user.getName());
        outService.addCheck(map);
        return new JsonResult(null,null,"success");
    }
}
