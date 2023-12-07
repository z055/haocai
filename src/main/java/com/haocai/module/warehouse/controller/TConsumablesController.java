package com.haocai.module.warehouse.controller;

import com.haocai.module.system.vo.User;
import com.haocai.module.warehouse.entity.TConsumables;
import com.haocai.module.warehouse.service.TConsumablesService;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/TConsumables")
public class TConsumablesController {

    @Autowired
    private TConsumablesService tConsumablesService;

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    private User getUser (HttpServletRequest request) {
        return (User)request.getSession().getAttribute("user");
    }

    /**
     * 获取库存
     * @param tConsumables
     * @param session
     * @return 所属学院的所有库存以及查询条数
     */
    @ResponseBody
    @RequestMapping("/getCon")
    public String getCon (TConsumables tConsumables, HttpSession session) {
        User user= (User) session.getAttribute("user");
        tConsumables.setDepartment(user.getDeptName());
        List<TConsumables> tConsumables1= tConsumablesService.getCon(tConsumables);
        return new JsonTemplate("查询成功",tConsumables1,tConsumablesService.count(tConsumables)).toString();
    }

    /**
     * 外借
     * @param tConsumables
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCon2")
    public String getCon2 (TConsumables tConsumables) {
        System.err.println("getCon2=======>" + tConsumables);
        List<TConsumables> tConsumables1= tConsumablesService.getCon2(tConsumables);
        return new JsonTemplate("查询成功",tConsumables1,tConsumablesService.count2(tConsumables)).toString();
    }


    /**
     *
     * @param tConsumables
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOutCon")
    public String addOutCon (TConsumables tConsumables, HttpServletRequest request) {
        System.err.println(tConsumables.toString());
        User user = this.getUser(request);

        System.out.println("tConsumables22="+tConsumables.toString()+"userName="+user.toString());
        tConsumables.setApplicant(user.getAccount());
        int index = tConsumablesService.addOutCon(tConsumables);
        return new JsonTemplate("成功","",index).toString();
    }

    @ResponseBody
    @RequestMapping("/queryPurchase")
    public String queryPurchase (TConsumables tConsumables) {
        System.out.println("queryPurchase="+tConsumables.toString());
        List<TConsumables> tConsumables1= tConsumablesService.queryPurchase(tConsumables);
        System.err.println(tConsumables1.toString());
        return new JsonTemplate("查询成功",tConsumables1,tConsumables1.size()).toString();
    }

    @ResponseBody
    @RequestMapping("/addInCon")
    public String addInCon (TConsumables tConsumables, HttpServletRequest request) {
        User user = this.getUser(request);
        if (tConsumables.getInNumber() > Math.toIntExact(tConsumables.getNumber())) {
            return new JsonTemplate("库存不足","",0).toString();
        } else if (tConsumables.getInNumber() < 0) {
            return new JsonTemplate("请输入大于0的数","",0).toString();
        }
        System.out.println("tConsumables33="+tConsumables.toString()+"userName="+user.toString());
        tConsumables.setApplicant(user.getAccount());
        int index = tConsumablesService.addInCon(tConsumables);
        return new JsonTemplate("成功","",index).toString();
    }
}
