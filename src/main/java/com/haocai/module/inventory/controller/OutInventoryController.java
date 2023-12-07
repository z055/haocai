package com.haocai.module.inventory.controller;

import com.haocai.module.inventory.service.OutInventoryService;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/OutInventory")
public class OutInventoryController {
    @Autowired
    private OutInventoryService outInventoryService;
    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getInfo(InventoryParam inventoryParam, HttpSession session){
        inventoryParam= (InventoryParam) ConversionStrUtil.ConversionStr(inventoryParam);
        User user=(User)session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")){
            inventoryParam.setDepartment(user.getDeptName());
        }
        System.err.println(inventoryParam.getDepartment());
        InventoryResult result=  outInventoryService.getInfo(inventoryParam);
        return new JsonTemplate("masg",result.getList(),result.getCount()).toString();
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAll(InventoryParam inventoryParam,HttpSession session){
        inventoryParam = (InventoryParam)ConversionStrUtil.ConversionStr(inventoryParam);
        User user=(User)session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")){
            inventoryParam.setDepartment(user.getDeptName());
        }
        InventoryResult all= outInventoryService.getAllPrices(inventoryParam);
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
