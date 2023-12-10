package com.haocai.module.inventory.controller;

import com.haocai.module.inventory.service.InInventoryService;
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

/**
 * @description:  盘点管理 === page/inventory
 * @return:
 * @author: 18380
 * @time: 2023/12/10 20:09
 */
@Controller
@RequestMapping("/InInventory")
public class InInventoryController {



    //用于部门的判断
    @ResponseBody
    @RequestMapping("/getUserDep")
    public Object getUserRole(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String deptName = user.getDeptName();
        return deptName;
    }

    @Autowired
    private InInventoryService IninventoryService;

    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getInfo(InventoryParam inventoryParam, HttpSession session) {
        inventoryParam = (InventoryParam) ConversionStrUtil.ConversionStr(inventoryParam);
        User user = (User) session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")) {
            inventoryParam.setDepartment(user.getDeptName());
        }
        InventoryResult result = IninventoryService.getInfo(inventoryParam);
        System.err.println(result);
        JsonTemplate jsonTemplate = new JsonTemplate("查询成功", result.getList(), result.getCount());
        return jsonTemplate.toString();
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAll(InventoryParam inventoryParam, HttpSession session) {
        inventoryParam = (InventoryParam) ConversionStrUtil.ConversionStr(inventoryParam);
        User user = (User) session.getAttribute("user");
        if (!user.getDeptName().equals("教务处")) {
            inventoryParam.setDepartment(user.getDeptName());
        }
        InventoryResult all = IninventoryService.getAllPrices(inventoryParam);
        BigDecimal allPri = BigDecimal.ZERO;
        List<InventoryVo> list = all.getList();
        for (InventoryVo vo : list) {
//            BigDecimal prices = new BigDecimal(vo.getPrices());
            BigDecimal price = new BigDecimal(vo.getPrice());
            BigDecimal num = new BigDecimal(vo.getNumber());
            BigDecimal prices;
            prices = price.multiply(num);
            allPri = allPri.add(prices);
        }
        return allPri.toString();
    }

}
