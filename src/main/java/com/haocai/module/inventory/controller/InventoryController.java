package com.haocai.module.inventory.controller;

import com.haocai.module.inventory.service.InventoryService;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    //获取库存盘点信息
    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getInfo(InventoryParam inventoryParam, HttpSession session){
        inventoryParam= (InventoryParam) ConversionStrUtil.ConversionStr(inventoryParam);
        User user=(User)session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")){
            inventoryParam.setDepartment(user.getDeptName());
        }
        System.err.println(inventoryParam.getDepartment());
        InventoryResult inventoryResult=inventoryService.getInfo(inventoryParam);
        List<InventoryVo> data=inventoryResult.getList();
        System.out.println(data);
        return new JsonTemplate("查询成功",data,inventoryResult.getCount()).toString();
    }

    @RequestMapping("/getTraining")
    @ResponseBody
    public Object getTraining(){
        List list=inventoryService.getTraining();
        return new JsonResult("0",list,"获取成功");
    }
    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAll(InventoryParam inventoryParam,HttpSession session){
        inventoryParam = (InventoryParam)ConversionStrUtil.ConversionStr(inventoryParam);
        User user=(User)session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")){
            inventoryParam.setDepartment(user.getDeptName());
        }
        InventoryResult all= inventoryService.getAll(inventoryParam);
        BigDecimal allPri = BigDecimal.ZERO;
        List<InventoryVo> list = all.getList();
        for (InventoryVo vo : list) {
            BigDecimal price = new BigDecimal(vo.getPrice());
            BigDecimal num = new BigDecimal(vo.getNumber());
            BigDecimal prices ;
            prices=price.multiply(num);
            allPri=allPri.add(prices);
        }
        return allPri.toString();
    }
}
