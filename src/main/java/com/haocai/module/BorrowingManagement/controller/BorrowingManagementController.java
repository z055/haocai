package com.haocai.module.BorrowingManagement.controller;

import com.haocai.module.BorrowingManagement.serviceImpl.BorrowingManagementServiceImpl;
import com.haocai.module.BorrowingManagement.vo.param.BorroParam;
import com.haocai.module.BorrowingManagement.vo.param.UpParam;
import com.haocai.module.BorrowingManagement.vo.resulet.BorResulet;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;




@Controller
@RequestMapping("/BorrowingManagement")
public class BorrowingManagementController {
    public Logger log = Logger.getLogger(this.getClass());
    @Autowired
    public BorrowingManagementServiceImpl borrowingManagementService;
    @RequestMapping("getAllConsumables")
    @ResponseBody
    public Object getAllConsumables (){
        return borrowingManagementService.getAllConsumables();
    }
    /**
     * @return
     * 修改耗材状态 前端传递状态码
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Object updateStatus(@RequestBody UpParam upParam){

       int resulet= borrowingManagementService.updateStatus(upParam);
       if (resulet!=0){
           return new JsonResult(JsonResult.SUCCESS,resulet,"修改成功");
       }
       else {
           return new JsonResult(JsonResult.FALL,resulet,"修改失败");
       }
    }
    /**
     * @return
     * 搜索功能 查询是根据耗材的二级学院
     */
    @RequestMapping("searchQuery")
    @ResponseBody
    public Object searchQuery(BorroParam borroParam, HttpSession session){
        borroParam= (BorroParam) ConversionStrUtil.ConversionStr(borroParam);
        borroParam.setDepartment((String) session.getAttribute("department"));
        BorResulet resulet=borrowingManagementService.searchQuery(borroParam);
        if (resulet.getList()!=null){
            return  new JsonTemplate("查询成功",resulet.getList(),resulet.getCount()).toString();
        }
        else {
            return  new JsonTemplate("查询失败",null,resulet.getCount()).toString();
        }
    }
    /**
     * 获取session里面的用户信息
     */
    @RequestMapping("getDepartment")
    @ResponseBody
    public Object getDepartment(HttpSession session){
       String zhiwei=(String) session.getAttribute("zhiwei");
       String[] zhiweis=zhiwei.split(",");
        System.err.println(zhiwei);
        System.err.println(session.getAttribute("department"));
        return session.getAttribute("department");
    }
}
